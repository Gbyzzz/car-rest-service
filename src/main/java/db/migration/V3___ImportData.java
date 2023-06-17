package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class V3___ImportData extends BaseJavaMigration {

//    private final ResourceLoader resourceLoader;

    private static final String COMMA_DELIMITER = ",";
    private static String [] lineValues;
    private static int brandId;
    private static int typeId;
    private static final String SQL_ADD_BRAND = "INSERT INTO brand (name)" +
            " values (?) ON CONFLICT (name) DO UPDATE SET name = ? RETURNING id;";
    private static final String SQL_UPDATE_BRAND_SEQ = " SELECT setval('brand_id_seq', " +
            "(SELECT MAX(id) from brand));";

    private static final String SQL_UPDATE_TYPE_SEQ = " SELECT setval('type_id_seq', " +
            "(SELECT MAX(id) from type));";
    private static final String SQL_ADD_TYPE = "INSERT INTO type (name)" +
            " values (?) ON CONFLICT (name) DO UPDATE SET name = ? RETURNING id";
    private static final String SQL_ADD_MODEL = "INSERT INTO model" +
            " (id, brand_id, year, name) values (?, ?, ?, ?)";
    private static final String SQL_ADD_CAR_MODEL_TYPE = "INSERT INTO" +
            " car_model_type (model_id, type_id) values (?,?)";

    @Override
    public void migrate(Context context) throws Exception {

//        Resource resource = resourceLoader.getResource("classpath:import/cars.csv");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass()
                .getResourceAsStream("/import/cars.csv")))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                lineValues = line.split(COMMA_DELIMITER);
                brandId = insertOneParam(context, SQL_ADD_BRAND, lineValues[1]);
                updateSeq(context, SQL_UPDATE_BRAND_SEQ);
                insertIntoModel(context);
                for (int i = 4; i < lineValues.length; i++) {
                    typeId = insertOneParam(context, SQL_ADD_TYPE, lineValues[i].replace("\"", "").replace(" ", ""));
                    updateSeq(context, SQL_UPDATE_TYPE_SEQ);
                    insertIntoCarModelType(context);
                }
            }
        }
    }

    private int insertOneParam(Context context, String sql, String param) {
        int generatedId = 0;

        try(PreparedStatement statement = context.getConnection()
                .prepareStatement(sql)) {
            statement.setString(1, param);
            statement.setString(2, param);
            ResultSet generatedKeys = statement.executeQuery();

            while (generatedKeys.next()){
                generatedId = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return generatedId;
    }

    private void updateSeq(Context context, String sql) {
        try(PreparedStatement statement = context.getConnection()
                .prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertIntoModel(Context context) {
        try(PreparedStatement statement = context.getConnection()
                .prepareStatement(SQL_ADD_MODEL)) {
            statement.setString(1, lineValues[0]);
            statement.setInt(2, brandId);
            statement.setInt(3, Integer.parseInt(lineValues[2]));
            statement.setString(4, lineValues[3]);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertIntoCarModelType(Context context) {
        try (PreparedStatement statement = context.getConnection()
                .prepareStatement(SQL_ADD_CAR_MODEL_TYPE)) {
            statement.setString(1, lineValues[0]);
            statement.setInt(2, typeId);
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

