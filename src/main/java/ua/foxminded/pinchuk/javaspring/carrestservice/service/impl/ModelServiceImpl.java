package ua.foxminded.pinchuk.javaspring.carrestservice.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.ModelDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.mapper.ModelMapper;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.*;
import ua.foxminded.pinchuk.javaspring.carrestservice.repository.ModelRepository;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.BrandService;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.ModelService;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.TypeService;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.exception.ItemAlreadyExists;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.exception.ItemNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    private final BrandService brandService;
    private final TypeService typeService;

    private final EntityManager entityManager;
    private final ModelMapper modelMapper;

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "0123456789"
            + "abcdefghijklmnopqrstuvxyz";

    private static final int ID_LENGTH = 10;

    public ModelServiceImpl(ModelRepository modelRepository, BrandService brandService,
                            TypeService typeService, EntityManager entityManager, ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.brandService = brandService;
        this.typeService = typeService;
        this.entityManager = entityManager;
        this.modelMapper = modelMapper;
    }

    @Override
    public ModelDTO findById(String id) throws ItemNotFoundException {
        return modelRepository.findById(id).map(modelMapper).orElseThrow(
                () -> new ItemNotFoundException("Model with id " + id +
                        " not found"));
    }

    @Override
    public void saveOrUpdate(Model model) {
        modelRepository.save(model);
    }

    @Override
    public void remove(Model model) {
        modelRepository.delete(model);
    }

    @Override
    public List<ModelDTO> findAll() {
        return modelRepository.findAll().stream().map(modelMapper)
                .collect(Collectors.toList());
    }

    @Override
    public ModelDTO findAllByBrandAndNameAndYear(String brand, String name, Integer year) throws ItemNotFoundException {
        return modelRepository.getModelsByBrandNameIgnoreCaseAndNameIgnoreCaseAndYear(brand, name, year)
                .map(modelMapper).orElseThrow(
                        () -> new ItemNotFoundException("Model with brand " + brand + ", name " +
                                name + ", and year " + year + " not found"));
    }

    @Override
    public void add(String brandName, String name, Integer year, List<String> typeNames) throws ItemNotFoundException, ItemAlreadyExists {
        List<Type> typeList = new ArrayList<>();
        for (String typeName : typeNames) {
            if (typeService.typeExistsName(typeName)) {
                typeList.add(typeService.findByName(typeName));
            } else {
                typeList.add(typeService.add(typeName));
            }
        }
        Brand brand;
        if (brandService.brandExistsByName(brandName)) {
            brand = brandService.findByName(brandName);
        } else {
            brand = brandService.add(brandName);
        }
        String id = generateId();
        while(modelRepository.existsById(id)){
            id = generateId();
        }
        Model model = new Model(id, brand, year, name, typeList);

        saveOrUpdate(model);
    }

    @Override
    @Transactional
    public void remove(String brand, String name, Integer year) throws ItemNotFoundException {
        remove(modelRepository.getModelsByBrandNameIgnoreCaseAndNameIgnoreCaseAndYear(brand, name, year)
                .orElseThrow(() -> new ItemNotFoundException("Model with brand " + brand + ", name " +
                        name + ", and year " + year + " not found")));
    }

    @Override
    public List<ModelDTO> searchModel(String brandName, String name, Integer yearMin, Integer yearMax,
                                      String type, Integer page, Integer pageSize) throws ItemNotFoundException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Model> criteriaQuery = criteriaBuilder.createQuery(Model.class);
        Root<Model> root = criteriaQuery.from(Model.class);
        List<Predicate> predicates = new ArrayList<>();

        if(brandName != null) {
            predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("brand").get("name")), brandName.toLowerCase()));
        }
        if (name != null) {
            predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("name")), name.toLowerCase()));
        }

        if (yearMin != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("year"), yearMin));
        }
        if (yearMax != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("year"), yearMax));
        }
        if (type != null) {
            Subquery<Model> subquery = criteriaQuery.subquery(Model.class);
            Root<Model> subRoot = subquery.from(Model.class);
            Join<Model, Type> typeJoin = subRoot.join("types");
            subquery.select(subRoot);

            Predicate typePredicate = criteriaBuilder.equal(criteriaBuilder.
                    lower(typeJoin.get("name")), type.toLowerCase());
            subquery.where(typePredicate);

            predicates.add(criteriaBuilder.in(root).value(subquery));
        }


        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Model> query = entityManager.createQuery(criteriaQuery);
        if (page != null && pageSize != null) {
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        if (query.getResultList().size() == 0) {
            brandService.findByName(brandName);
            throw new ItemNotFoundException("No model was found with such filters");
        }
        return query.getResultList().stream().map(modelMapper).collect(Collectors.toList());
    }

    private String generateId() {
        StringBuilder sb = new StringBuilder(ID_LENGTH);
        for (int i = 0; i < ID_LENGTH; i++) {
            int index = (int) (ALPHA_NUMERIC_STRING.length() * Math.random());
            sb.append(ALPHA_NUMERIC_STRING.charAt(index));
        }
        return sb.toString();
    }
}
