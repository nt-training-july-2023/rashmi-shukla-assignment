import React, { useEffect, useState } from 'react'
import CategoryService from '../../Services/CategoryService';
import { useParams } from 'react-router-dom';
import Navbar from '../../Components/Navbar/Navbar';
import { useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';
import QuizService from '../../Services/QuizService';

const Quizzes = () => {
    const [quizzes, setQuizzes] = useState([]);
    const navigate = useNavigate();
    const {id} = useParams();

    useEffect(()=>{
        getQuizzesByCategory();
    },[])

    const getQuizzesByCategory= () =>{
        CategoryService.getQuizzesByCategory(id).then(response => {
            setQuizzes(response.data);
            console.log(response.data);
        }).catch((error) => {
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
            getQuizzesByCategory();
      })
      .catch((error) => {
          console.log(error);
      });
  }


  return (
    <div>
      <Navbar/>
      <div className='quiz-header'>
            <h1>CATEGORY:{id}</h1>
      </div>
      <div className='quiz-container'>
      {quizzes.map((quizItem) => (      
          <div key={quizItem.quizId} className="card">
            <div className="card-body">
              <h5 className="card-title">{quizItem.quizTitle}</h5>
              <p className="card-text">{quizItem.quizDescription}</p>
              <p className="card-text">Quiz Timer: {quizItem.quizTimer}</p>
              <div className="card-text">
                  <div>
                    <h6>Category: {quizItem.category.categoryTitle}</h6>
                    <p>{quizItem.category.categoryDescription}</p>
                  </div>
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

export default Quizzes
