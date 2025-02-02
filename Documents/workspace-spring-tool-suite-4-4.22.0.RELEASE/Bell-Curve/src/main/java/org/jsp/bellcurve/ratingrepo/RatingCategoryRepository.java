package org.jsp.bellcurve.ratingrepo;



import org.jsp.bellcurve.RatingCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingCategoryRepository extends JpaRepository<RatingCategory, String> {
}
