package ua.foxminded.pinchuk.javaspring.carrestservice.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.Car;
import ua.foxminded.pinchuk.javaspring.carrestservice.repository.CarRepository;
import ua.foxminded.pinchuk.javaspring.carrestservice.service.CarService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final EntityManager entityManager;


    public CarServiceImpl(CarRepository carRepository, EntityManager entityManager) {
        this.carRepository = carRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Car findById(int id) throws Exception {
        return carRepository.findById(id).orElseThrow(
                ()->new Exception("Car with id " + id + " not found"));
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
    public List<Car> searchCar(String brandName, Integer yearMin, Integer yearMax,
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
        return query.getResultList();    }
}
