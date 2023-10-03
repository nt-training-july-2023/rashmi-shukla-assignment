import React, { useEffect, useState } from "react";
import QuizService from "../../Services/QuizService";
import Swal from "sweetalert2";
import Navbar from "../../Components/Navbar/Navbar";
import "./Quiz.css";
import instructions from "./Instructions";
import { useNavigate, useParams } from "react-router-dom";
import CategoryService from "../../Services/CategoryService";
import PageHeader from "../../Components/Header/PageHeader";

const ListQuiz = () => {
  const [quiz, setQuiz] = useState([]);
  const [categoryName, setCategoryName] = useState("");
  const navigate = useNavigate();
  const userRole = localStorage.getItem("role");
  const { id } = useParams();

  useEffect(() => {
    if (id) {
      getQuizzesByCategory();
      getCategoryById();
    } else {
      getAllQuizzes();
    }
  }, [id]);

  const getQuizzesByCategory = () => {
    CategoryService.getQuizzesByCategory(id)
      .then((response) => {
        setQuiz(response.data);
      })
      .catch((error) => {});
  };

  const getCategoryById = () => {
    CategoryService.getCategoryById(id)
      .then((response) => {
        setCategoryName(response.data.categoryTitle);
      })
      .catch((error) => {});
  };

  const getAllQuizzes = () => {
    QuizService.getAllQuizzes()
      .then((response) => {
        setQuiz(response.data);
      })
      .catch((error) => {});
  };

  const deleteQuiz = (quizId) => {
    QuizService.deleteQuiz(quizId)
      .then((response) => {
        Swal.fire({
          title: "Success",
          text: response.data.message,
          icon: "success",
          timer: 2000,
          showConfirmButton: false,
        });
        getAllQuizzes();
      })
      .catch((error) => {});
  };

  const heading = () => {
    if (id) {
      return `CATEGORY: ${categoryName}`;
    } else {
      return "QUIZZES";
    }
  };

  return (
    <div>
      <Navbar />
      <PageHeader
        className="quiz-header"
        heading={heading()}
        displayButton="true"
        onClick={() => navigate(`/quizzes/add`)}
        name="Add Quiz"
      />
      <div className="quiz-container">
        {quiz.map((quizItem) => (
          <div key={quizItem.quizId} className="quiz-card">
            <div className="quiz-card-body">
              <h5 className="quiz-card-title">Quiz: {quizItem.quizTitle}</h5>
              <p className="quiz-card-text">{quizItem.quizDescription}</p>
              <p className="quiz-card-text">
                Time limit: {quizItem.quizTimer} minutes
              </p>
              <div className="quiz-card-text">
                <div>
                  <h5>Category: {quizItem.category.categoryTitle}</h5>
                  <h6>{quizItem.category.categoryDescription}</h6>
                </div>
              </div>
              {userRole === "admin" ? (
                <>
                  <button
                    className="action-btn view-btn"
                    onClick={() =>
                      navigate(`/quizzes/${quizItem.quizId}/questions`)
                    }
                  >
                    View Questions
                  </button>
                  <button
                    className="action-btn update-btn"
                    onClick={() =>
                      navigate(`/quizzes/update/${quizItem.quizId}`)
                    }
                  >
                    Update Quiz
                  </button>
                  <button
                    className="action-btn delete-btn"
                    onClick={() =>
                      Swal.fire({
                        title: "Warning",
                        text: "Delete Quiz?",
                        icon: "warning",
                        confirmButtonText: "Delete",
                        confirmButtonColor: "red",
                        showCancelButton: true,
                      }).then((result) => {
                        if (result.isConfirmed) {
                          deleteQuiz(quizItem.quizId);
                        }
                      })
                    }
                  >
                    {" "}
                    Delete{" "}
                  </button>
                </>
              ) : (
                <button
                  className="action-btn start-btn"
                  onClick={() =>
                    Swal.fire({
                      title: "Instructions",
                      icon: "info",
                      html: instructions,
                      confirmButtonText: "Start Quiz",
                      showCancelButton: true,
                    }).then((result) => {
                      if (result.isConfirmed) {
                        navigate(`/quizzes/${quizItem.quizId}/questions`);
                      }
                    })
                  }
                >
                  Start Assesment
                </button>
              )}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ListQuiz;
