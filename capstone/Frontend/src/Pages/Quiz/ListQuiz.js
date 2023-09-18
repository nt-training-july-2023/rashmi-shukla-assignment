import React, { useEffect, useState } from 'react'
import QuizService from '../../Services/QuizService';
import Swal from 'sweetalert2';
import Navbar from '../../Components/Navbar/Navbar';
import './Quiz.css';
import { useNavigate } from 'react-router-dom';

const ListQuiz = () => {

    const[quiz, setQuiz] = useState([]);
    const navigate = useNavigate();
    const userRole = localStorage.getItem("role");

    useEffect(()=>{
        getAllQuizzes();
    },[]);

    const getAllQuizzes =() =>{
        QuizService.getAllQuizzes().then((response)=>{
            setQuiz(response.data)
        })
        .catch((error) => {
            console.log(error);
        })
    }

    const deleteQuiz = (quizId) =>{
        QuizService.deleteQuiz(quizId).then((response) =>{
            Swal.fire({
                title: "Success",
                text: "Quiz deleted successfully",
                icon: "success",
                timer:2000,
                showConfirmButton:false
              });
            getAllQuizzes();
        })
        .catch((error) => {
            console.log(error);
        });
    }

  return (
    <div>
      <Navbar/>
      <div className='quiz-header'>
            <h1>ALL QUIZZES</h1>
            {userRole === "admin" && (
            <button onClick={()=> navigate(`/addQuiz`)}>
                Add Quiz
            </button>
            )}
      </div>
      <div className='quiz-container'>
      {quiz.map((quizItem) => (
          <div key={quizItem.quizId} className="quiz-card">
            <div className="quiz-card-body">
              <h5 className="quiz-card-title">Quiz: {quizItem.quizTitle}</h5>
              <p className="quiz-card-text">{quizItem.quizDescription}</p>
              <p className="quiz-card-text">Time limit: {quizItem.quizTimer} minutes</p>
              <div className="quiz-card-text">
                  <div>
                    <h5>Category: {quizItem.category.categoryTitle}</h5>
                    <h6>{quizItem.category.categoryDescription}</h6>
                  </div>
              </div>
              {userRole==="admin" ? (
              <>
              <button className='action-btn update-btn' onClick={()=> navigate(`/UpdateQuiz/${quizItem.quizId}`)}>Update</button>
              <button
                className='action-btn delete-btn'
                onClick={() => 
                    Swal.fire({
                        title: "Warning",
                        text: "Delete Quiz?",
                        icon: "warning",
                        confirmButtonText:"delete",
                        confirmButtonColor:"red",
                        showCancelButton:true
                      }).then((result)=>{ if(result.isConfirmed) {
                        deleteQuiz(quizItem.quizId)}
                    } ) }> Delete </button>
              </>):(
                <button className='action-btn start-btn'>Start Assesment</button>
              )}
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}

export default ListQuiz
