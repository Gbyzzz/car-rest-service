package ua.foxminded.pinchuk.javaspring.carrestservice.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.CarDTO;
import ua.foxminded.pinchuk.javaspring.carrestservice.dto.mapper.CarMapper;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Car;
import ua.foxminded.pinchuk.javaspring.carrestservice.repository.CarRepository;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.CarService;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.exception.ItemNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final EntityManager entityManager;
    private final CarMapper carMapper;


    public CarServiceImpl(CarRepository carRepository, EntityManager entityManager, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.entityManager = entityManager;
        this.carMapper = carMapper;
    }

    @Override
    public CarDTO findById(Long id) throws ItemNotFoundException {
        return carRepository.findById(id).map(carMapper).orElseThrow(
                ()->new ItemNotFoundException("Car with id " + id + " not found"));
    }

    @Override
    public void saveOrUpdate(Car car) {
        carRepository.save(car);
    }

    @Override
    public void remove(Car car) {
        carRepository.delete(car);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public List<CarDTO> searchCar(String brandName, Integer yearMin, Integer yearMax,
                                  String type, String color, String modelName, Integer page,
                                  Integer pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
        Root<Car> root = criteriaQuery.from(Car.class);
        List<Predicate> predicates = new ArrayList<>();

        if (brandName != null) {
            predicates.add(criteriaBuilder.equal(
                    criteriaBuilder.lower(root.get("carModelType")
                            .get("model").get("brand").get("name")), brandName.toLowerCase()));
        }
        if (yearMin != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("carModelType")
                    .get("model").get("year"), yearMin));
        }
        if (yearMax != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("carModelType")
                    .get("model").get("year"), yearMax));
        }
        if (type != null) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder
                    .lower(root.get("carModelType").get("type").get("name")),
                            type.toLowerCase()));
        }
        if (color != null) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("carColor")),
                    color.toLowerCase()));
        }
        if (modelName != null) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("carModelType")
                    .get("model").get("name")), modelName.toLowerCase()));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Car> query = entityManager.createQuery(criteriaQuery);
        if(page != null && pageSize != null) {
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        return query.getResultList().stream().map(carMapper).collect(Collectors.toList());
    }
}
