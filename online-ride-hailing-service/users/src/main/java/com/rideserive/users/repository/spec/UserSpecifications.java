package com.rideserive.users.repository.spec;

import com.rideserive.users.entities.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {
    public static Specification<User> hasFirstName(String firstName) {
        return (root, query, builder) -> firstName == null ? null : builder.equal(root.get("firstName"), firstName);
    }

    public static Specification<User> hasLastName(String lastName) {
        return (root, query, builder) -> lastName == null ? null : builder.equal(root.get("lastName"), lastName);
    }
}
