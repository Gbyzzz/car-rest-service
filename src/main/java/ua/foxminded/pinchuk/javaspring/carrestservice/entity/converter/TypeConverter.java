package ua.foxminded.pinchuk.javaspring.carrestservice.entity.converter;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import ua.foxminded.pinchuk.javaspring.carrestservice.entity.User;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

public class TypeConverter implements UserType<User.Role> {
    @Override
    public int getSqlType() {
        return Types.OTHER;
    }

    @Override
    public Class<User.Role> returnedClass() {
        return User.Role.class;
    }

    @Override
    public boolean equals(User.Role role, User.Role j1) {
        return role.name().equals(j1.name());
    }

    @Override
    public int hashCode(User.Role role) {
        return Objects.hashCode(role);
    }

    @Override
    public User.Role nullSafeGet(ResultSet resultSet, int i,
                                    SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws SQLException {
        return User.Role.valueOf(resultSet.getString(i));
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, User.Role role,
                            int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {
        if(role!=null) {
            preparedStatement.setObject(i, role.name(), Types.OTHER);
        }
    }

    @Override
    public User.Role deepCopy(User.Role role) {
        return role;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(User.Role role) {
        return null;
    }

    @Override
    public User.Role assemble(Serializable serializable, Object o) {
        return null;
    }

    @Override
    public User.Role replace(User.Role role, User.Role j1, Object o) {
        return j1;
    }
}
