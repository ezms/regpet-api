package com.regpet.api.repositories;

import com.regpet.api.dto.requests.UserLoginDTO;
import com.regpet.api.models.User;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import java.util.UUID;

@RequestScoped
public class UserRepository extends BaseRepository<User, UUID> {

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    public boolean isEmailAlreadyRegistered(String email) {
        return (boolean) entityManager.createNativeQuery("""
                    SELECT EXISTS(
                        SELECT u.*
                            FROM public.users u
                            WHERE u.email = :email
                    ) AS is_already_exists
                    """)
                .setParameter("email", email)
                .getSingleResult();
    }

    public UserLoginDTO getLoginData(String email) {
        TypedQuery<Tuple> query = entityManager.createQuery(
                "SELECT u.email, u.password FROM User u WHERE u.email = :email", Tuple.class);
        query.setParameter("email", email);
        Tuple tuple = query.getSingleResult();
        assert tuple != null;
        return new UserLoginDTO((String) tuple.get(0), (String) tuple.get(1));
    }
}
