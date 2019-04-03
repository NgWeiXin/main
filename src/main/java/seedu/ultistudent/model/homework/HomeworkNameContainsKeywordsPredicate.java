package seedu.ultistudent.model.homework;

import java.util.List;
import java.util.function.Predicate;

import seedu.ultistudent.commons.util.StringUtil;

/**
 * Tests that a {@code Homework}'s {@code HomeworkName} matches any of the keywords given.
 */
public class HomeworkNameContainsKeywordsPredicate implements Predicate<Homework> {
    private final List<String> keywords;

    public HomeworkNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Homework homework) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(homework.getHomeworkName().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HomeworkNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((HomeworkNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
