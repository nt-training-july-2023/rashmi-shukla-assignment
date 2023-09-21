import React, { useEffect, useState } from "react";
import QuestionService from "../../Services/QuestionService";
import { useNavigate, useParams } from "react-router-dom";
import Navbar from "../../Components/Navbar/Navbar";
import "./Question.css";
import QuizService from "../../Services/QuizService";
import Swal from "sweetalert2";

const ListQuestion = () => {
  const currentDate = new Date();
  const dateTime = `${currentDate.getDate()}-${
    currentDate.getMonth() + 1
  }-${currentDate.getFullYear()} ${currentDate.getHours()}:${currentDate.getMinutes()}:${currentDate.getSeconds()}`;
  const [questions, setQuestions] = useState([]);
  const [totalMarks, setTotalMarks] = useState(0);

  const [obtainedMarks, setObtainedMarks] = useState(0);
  const [submitted, setSubmitted] = useState(false);
  const [selectedAnswers, setSelectedAnswers] = useState({});

  const userName = localStorage.getItem("userName");
  const userEmail = localStorage.getItem("userEmail");
  const [quizName, setQuizName] = useState([]);
  const [categoryName, setCategoryName] = useState("");

  const navigate = useNavigate();
  const userRole = localStorage.getItem("role");
  const { id } = useParams();

  useEffect(() => {
    getQuestionsByQuiz();
    getQuizById();
  }, []);

  const getQuestionsByQuiz = () => {
    QuizService.getQuestionsByQuiz(id)
      .then((response) => {
        setQuestions(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const getQuizById = () => {
    QuizService.getQuizById(id)
      .then((reponse) => {
        setQuizName(reponse.data.quizTitle);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const deleteQuestion = (questionId) => {
    QuestionService.deleteQuestion(questionId)
      .then((response) => {
        Swal.fire({
          title: "Success",
          text: "Quiz deleted successfully",
          icon: "success",
          timer: 2000,
          showConfirmButton: false,
        });
        getQuestionsByQuiz();
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const handleOptionChange = (questionId, selectedOption, correctAnswer) => {
    if (!submitted) {
      setSelectedAnswers((prevSelectedAnswers) => ({
        ...prevSelectedAnswers,
        [questionId]: selectedOption,
      }));
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setSubmitted(true);
    let score = 0;
    for (const question of questions) {
      const questionId = question.questionId;
      if (selectedAnswers[questionId] === question.answer) {
        score += 1;
      }
    }
    setObtainedMarks(score);
  };

  return (
    <div>
      {userRole === "admin" && <Navbar />}
      <div className="question-header">
        <h1>QUIZ: {quizName}</h1>
        {userRole === "admin" ? (
          <button onClick={() => navigate(`/quiz/${id}/addQuestion`)}>
            Add Question
          </button>
        ) : (
          <p>Timer: 00:60:00</p>
        )}
      </div>
      <div className="question-container">
        <form onSubmit={handleSubmit}>
          {questions.map((question, index) => (
            <div key={index} class="question-card">
              <div className="question-form">
                <h5 className="question-card-title">
                  Q{++index} : {question.questionTitle}
                </h5>

                {Object.values(question.options).map(
                  (optionValue, optionIndex) => (
                    <p key={optionIndex} className="question-card-text">
                      {optionIndex + 1}:
                      <input
                        type="radio"
                        name={`options-${question.questionId}`}
                        value={optionValue}
                        onChange={() =>
                          handleOptionChange(
                            question.questionId,
                            optionValue,
                            question.answer
                          )
                        }
                        checked={
                          selectedAnswers[question.questionId] === optionValue
                        }
                        disabled={submitted}
                      />
                      {optionValue}
                    </p>
                  )
                )}

                <br />
                {userRole == "admin" && (
                  <p className="question-card-text">
                    Correct answer: {question.answer}
                  </p>
                )}
              </div>
              {userRole === "admin" && (
                <div className="question-btns">
                  <button
                    className="ques-update-btn"
                    onClick={() =>
                      navigate(
                        `/quiz/${id}/updateQuestion/${question.questionId}`
                      )
                    }
                  >
                    Update
                  </button>
                  <button
                    className="ques-delete-btn"
                    onClick={() =>
                      Swal.fire({
                        title: "Warning",
                        text: "Delete Question?",
                        icon: "warning",
                        confirmButtonText: "delete",
                        confirmButtonColor: "red",
                        showCancelButton: true,
                      }).then((result) => {
                        if (result.isConfirmed) {
                          deleteQuestion(question.questionId);
                        }
                      })
                    }
                  >
                    {" "}
                    Delete{" "}
                  </button>
                </div>
              )}
            </div>
          ))}
          <div>
            <button onClick={handleSubmit} style={{backgroundColor:"blue", color:"white"}} disabled={submitted}>
                Submit Answers
            </button>
        </div>
        </form>
      </div>
    </div>
  );
};

export default ListQuestion;
