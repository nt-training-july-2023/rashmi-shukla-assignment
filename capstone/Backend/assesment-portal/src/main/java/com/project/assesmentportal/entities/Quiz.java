package com.project.assesmentportal.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a quiz.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "quizes")
public class Quiz {
    /**
     * The unique identifier of the quiz.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long quizId;

    /**
     * The title of the quiz.
     */
    @Column(name = "quiz_title", nullable = false, unique = true)
    private String quizTitle;

    /**
     * The description of the quiz.
     */
    @Column(name = "quiz_desc")
    private String quizDescription;

    /**
     * The timer for quiz.
     */
    @Column(nullable = false)
    private int quizTimer;

    /**
     * many to one relation between category and quiz.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Category category;

    /**
     * getter for category.
     * @return category the quiz belongs to.
     */
    public final Category getCategory() {
        if (category != null) {
            return new Category(category.getCategoryId(),
                    category.getCategoryTitle(),
                    category.getCategoryDescription());
        }
        return null;

    }

    /**
     * setter for the category.
     * @param cat which the quiz belongs to.
     */
    public final void setCategory(final Category cat) {
        if (cat != null) {
            this.category = new Category(cat.getCategoryId(),
                    cat.getCategoryTitle(), cat.getCategoryDescription());
        } else {
            this.category = null;
        }
    }

    /**
     * One to many relation between quiz and question.
     */
    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    /**
     * getter for list of questions belonging to this quiz.
     * @return list of questions.
     */
    public final List<Question> getQuestions() {
        return new ArrayList<>(questions);
    }

    /**
     * setter for list of questions belonging to this quiz.
     * @param questionsList list of questions.
     */
    public final void setQuestions(final List<Question> questionsList) {
        this.questions = new ArrayList<>(questionsList);
    }

    /**
     * all args constructor for quiz.
     * @param qId    unique id for quiz.
     * @param qTitle title of the quiz.
     * @param qDesc  description of the quiz.
     * @param time   timer for the quiz.
     * @param cat category.
     */
    public Quiz(final long qId, final String qTitle, final String qDesc,
            final int time, final Category cat) {
        this.quizId = qId;
        this.quizTitle = qTitle;
        this.quizDescription = qDesc;
        this.quizTimer = time;
        if (cat != null) {
            this.category = new Category(cat.getCategoryId(),
                    cat.getCategoryTitle(), cat.getCategoryDescription());
        } else {
            this.category = null;
        }
    }
}
