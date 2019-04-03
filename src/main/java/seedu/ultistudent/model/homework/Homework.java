package seedu.ultistudent.model.homework;

import static seedu.ultistudent.commons.util.CollectionUtil.requireAllNonNull;

import seedu.ultistudent.model.modulecode.ModuleCode;

/**
 * Represents a homework entry in the ultistudent.
 */
public class Homework {

    public static final String MESSAGE_CONSTRAINTS = "Homework should be inputted as MODULECODE; "
            + "HOMEWORKNAME; DEADLINE";

    private ModuleCode moduleCode;
    private HomeworkName homeworkName;
    private Date deadline;

    public Homework (ModuleCode moduleCode, HomeworkName homeworkName, Date deadline) {
        requireAllNonNull(moduleCode, homeworkName, deadline);

        this.moduleCode = moduleCode;
        this.homeworkName = homeworkName;
        this.deadline = deadline;
    }

    public HomeworkName getHomeworkName() {
        return homeworkName;
    }

    public Date getDeadline() {
        return deadline;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    @Override
    public String toString() {
        return moduleCode + "; " + homeworkName + "; " + deadline + "; ";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Homework // instanceof handles nulls
                && moduleCode.equals(((Homework) other).moduleCode)
                && homeworkName.equals(((Homework) other).homeworkName)
                && deadline.equals(((Homework) other).deadline));
    }

    @Override
    public int hashCode() {
        String value = moduleCode + " " + homeworkName + " " + deadline;
        return value.hashCode();
    }

}
