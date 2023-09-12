import React, { useEffect, useState } from 'react'
import QuizService from '../../Services/QuizService';
import Swal from 'sweetalert2';
import Navbar from '../../Components/Navbar/Navbar';
import './Quiz.css';
import { useNavigate } from 'react-router-dom';

const ListQuiz = () => {

    const[quiz, setQuiz] = useState([]);
    const navigate = useNavigate();

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
      <div className='page-title'>
            <h1>ALL QUIZZES</h1>
            <button onClick={()=> navigate(`/addQuiz`)}>
                Add Quiz
            </button>
      </div>
      <div className='quiz-container'>
      {quiz.map((quizItem) => (
          <div key={quizItem.quizId} className="card">
            <div className="card-body">
              <h5 className="card-title">{quizItem.quizTitle}</h5>
              <p className="card-text">{quizItem.quizDescription}</p>
              <p className="card-text">Quiz Timer: {quizItem.quizTimer}</p>
              <div className="card-text">
                {quizItem.category ? (
                  <div>
                    <h6>Category: {quizItem.category.categoryTitle}</h6>
                    <p>{quizItem.category.categoryDescription}</p>
                  </div>
                ) : (
                  <p>No category available</p>
                )}
              </div>
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
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}

export default ListQuiz
