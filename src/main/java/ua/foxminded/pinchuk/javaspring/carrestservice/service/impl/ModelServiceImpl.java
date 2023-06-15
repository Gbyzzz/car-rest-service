package ua.foxminded.pinchuk.javaspring.carrestservice.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Service;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.*;
import ua.foxminded.pinchuk.javaspring.carrestservice.repository.ModelRepository;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.BrandService;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.ModelService;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.TypeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    private final BrandService brandService;
    private final TypeService typeService;

    private final EntityManager entityManager;


    public ModelServiceImpl(ModelRepository modelRepository, BrandService brandService,
                            TypeService typeService, EntityManager entityManager) {
        this.modelRepository = modelRepository;
        this.brandService = brandService;
        this.typeService = typeService;
        this.entityManager = entityManager;
    }

    @Override
    public Model findById(String id) throws Exception {
        return modelRepository.findById(id).orElseThrow(
                () -> new Exception("Model with id " + id +
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
    public List<Model> findAll() {
        return modelRepository.findAll();
    }

    @Override
    public List<Model> findAllByBrand(String brand) {
        return modelRepository.getModelsByBrandNameIgnoreCase(brand);
    }

    @Override
    public List<Model> findAllByBrandAndName(String brand, String name) {
        return modelRepository.getModelsByBrandNameIgnoreCaseAndNameIgnoreCase(brand, name);
    }

    @Override
    public Model findAllByBrandAndNameAndYear(String brand, String name, Integer year) {
        return modelRepository.getModelsByBrandNameIgnoreCaseAndNameIgnoreCaseAndYear(brand, name, year);
    }

    @Override
    public void add(String brandName, String name, Integer year, List<String> typeNames) {
        List<Type> typeList = new ArrayList<>();
        for (String typeName : typeNames) {
            typeList.add(typeService.findByName(typeName));
        }
        Brand brand = brandService.findByName(brandName);
        Model model = new Model("2131", brand, year, name, typeList);

        saveOrUpdate(model);
    }

    @Override
    public void remove(String brand, String name, Integer year) {
        remove(modelRepository.getModelsByBrandNameIgnoreCaseAndNameIgnoreCaseAndYear(brand, name, year));
    }

    @Override
    public List<Model> searchModel(String brandName, String name, Integer yearMin, Integer yearMax,
                                   String type, Integer page, Integer pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Model> criteriaQuery = criteriaBuilder.createQuery(Model.class);
        Root<Model> root = criteriaQuery.from(Model.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.equal(
                criteriaBuilder.lower(root.get("brand").get("name")), brandName.toLowerCase()));

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

            Predicate typePredicate = criteriaBuilder.equal(criteriaBuilder.lower(typeJoin.get("name")), type.toLowerCase());
            subquery.where(typePredicate);

            predicates.add(criteriaBuilder.in(root).value(subquery));
        }


        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Model> query = entityManager.createQuery(criteriaQuery);
        if (page != null && pageSize != null) {
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        return query.getResultList();
    }
}
