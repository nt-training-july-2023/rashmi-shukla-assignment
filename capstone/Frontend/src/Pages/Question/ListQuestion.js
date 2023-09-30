import React, { useEffect, useState } from "react";
import QuestionService from "../../Services/QuestionService";
import { useNavigate, useParams } from "react-router-dom";
import Navbar from "../../Components/Navbar/Navbar";
import "./Question.css";
import QuizService from "../../Services/QuizService";
import Swal from "sweetalert2";
import ResultService from "../../Services/ResultService";

const ListQuestion = () => {
  const [questions, setQuestions] = useState([]);

  const currentDate = new Date();
  const dateTime = `${currentDate.getDate()}-${
    currentDate.getMonth() + 1
  }-${currentDate.getFullYear()} ${currentDate.getHours()}:${currentDate.getMinutes()}:${currentDate.getSeconds()}`;

  const [totalMarks, setTotalMarks] = useState(0);
  const [totalQuestions, setTotalQuestions] = useState(0);
  const userName = localStorage.getItem("userName");
  const userEmail = localStorage.getItem("userEmail");
  const [quizTitle, setQuizTitle] = useState([]);
  const [categoryTitle, setCategoryTitle] = useState("");

  const [attemptedQuestions, setAttemptedQuestions] = useState(0);
  const [obtainedMarks, setObtainedMarks] = useState(0);
  const [submitted, setSubmitted] = useState(false);
  const [selectedAnswers, setSelectedAnswers] = useState({});

  const navigate = useNavigate();
  const userRole = localStorage.getItem("role");
  const { id } = useParams();

  const [timeInSeconds, setTimeInSeconds] = useState(0);

  useEffect(() => {
    const handleCountdown = () => {
      if (timeInSeconds > 0) {
        setTimeInSeconds((prevTime) => prevTime - 1);
      } else {
        handleSubmit();
      }
    };
    const countdownInterval = setInterval(handleCountdown, 1000);
    // Clean up the interval when the component unmounts
    return () => clearInterval(countdownInterval);
  }, [timeInSeconds]);

  // Format the remaining time as HH:MM:SS
  const formattedTime = new Date(timeInSeconds * 1000).toISOString().substr(11, 8);

  useEffect(() => {
    getQuestionsByQuiz();
    getQuizById();
  }, []);

  const getQuestionsByQuiz = () => {
    QuizService.getQuestionsByQuiz(id)
      .then((response) => {
        setQuestions(response.data);
        setTotalQuestions(response.data.length);
        setTotalMarks(response.data.length);  
      })
      .catch((error) => {
      });
  };

  const getQuizById = () => {
    QuizService.getQuizById(id)
      .then((response) => {
        setQuizTitle(response.data.quizTitle);
        setCategoryTitle(response.data.category.categoryTitle);
        const timerInMinutes = response.data.quizTimer;
        const timerInSeconds = timerInMinutes * 60; 
        setTimeInSeconds(timerInSeconds);
      })
      .catch((error) => {
      });
  };

  const deleteQuestion = (questionId) => {
    QuestionService.deleteQuestion(questionId)
      .then(() => {
        Swal.fire({
          title: "Success",
          text: "Question deleted successfully",
          icon: "success",
          timer: 2000,
          showConfirmButton: false,
        });
        
        getQuestionsByQuiz();
      })
      .catch((error) => {
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
    if(e){
    e.preventDefault();
    }
    setSubmitted(true);
    let score = 0;
    for (const question of questions) {
      const questionId = question.questionId;
      if (selectedAnswers[questionId] === question.answer) {
        score += 1;
      }
    }
    setObtainedMarks(score);
    setAttemptedQuestions(Object.keys(selectedAnswers).length);

    const result = {totalMarks,obtainedMarks: score, attemptedQuestions: Object.keys(selectedAnswers).length, totalQuestions, dateTime, userEmail, userName, quizTitle, categoryTitle }
    ResultService.addResult(result)
    .then((response) => {
      Swal.fire({
        title: "Success",
        text: "Quiz submitted successfully",
        icon: "success",
        timer: 2000,
        showConfirmButton: false,
      });
    })
    navigate("/user-dashboard")
  };

  return (
    <div>
      {userRole === "admin" && <Navbar />}
      <div className="question-header">
        <h1>QUIZ: {quizTitle}</h1>
        {userRole === "admin" ? (
          <button onClick={() => navigate(`/quizzes/${id}/questions/add`)}>
            Add Question
          </button>
        ) : (
          <>
          <p>Time Remaining: {formattedTime}</p>
          <button onClick={handleSubmit} disabled={submitted}>
                Submit Quiz
          </button>
          </>
        )}
      </div>
      <div className="question-container">
          {questions.map((question, index) => (
            <div key={index} class="question-card">
              <div className="question-form">
                <h5 className="question-card-title">
                  Q{++index} : {question.questionTitle}
                </h5>

                {Object.values(question.options).map(
                  (optionValue, optionIndex) => (
                    <p key={optionIndex} className="question-card-text">
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
                {userRole === "admin" && (
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
                        `/quizzes/${id}/questions/update/${question.questionId}`
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
                        confirmButtonText: "Delete",
                        confirmButtonColor: "red",
                        showCancelButton: true,
                      }).then((result) => {
                        if (result.isConfirmed) {
                          deleteQuestion(question.questionId);
                        }
                      })
                    }
                  >
                    Delete
                  </button>
                </div>
              )}
            </div>
          ))}
      </div>
    </div>
  );
};

export default ListQuestion;
