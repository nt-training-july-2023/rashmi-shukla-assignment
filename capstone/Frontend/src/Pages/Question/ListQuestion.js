import React, { useEffect, useState } from 'react'
import QuestionService from '../../Services/QuestionService';
import { useNavigate, useParams } from 'react-router-dom';
import Navbar from '../../Components/Navbar/Navbar';
import './Question.css'
import QuizService from '../../Services/QuizService';
import Swal from 'sweetalert2';

const ListQuestion = () => {
    const [questions, setQuestions] = useState([]);
    const [quizName, setQuizName] = useState([]);
    const navigate = useNavigate();
    const userRole = localStorage.getItem("role");
    const {id} = useParams();

    useEffect(()=>{
        getQuestionsByQuiz();
        getQuizById();
    },[]);

    const getQuestionsByQuiz =() =>{
        QuizService.getQuestionsByQuiz(id).then(response =>{
            setQuestions(response.data);
        }).catch((error) =>{
            console.log(error);
        })
    }

    const getQuizById=() =>{
        QuizService.getQuizById(id).then(reponse =>{
            setQuizName(reponse.data.quizTitle);
        }).catch((error) => {
            console.log(error);
        })
    }

    const deleteQuestion = (questionId) =>{
        QuestionService.deleteQuestion(questionId).then((response) =>{
            Swal.fire({
                title: "Success",
                text: "Quiz deleted successfully",
                icon: "success",
                timer:2000,
                showConfirmButton:false
              });
            getQuestionsByQuiz();
        })
        .catch((error) => {
            console.log(error);
        });
    }

  return (
    <div>
        <Navbar/>
      <div className='question-header'>
            <h1>QUIZ: {quizName}</h1>
            {userRole === "admin" && (
            <button onClick={()=> navigate(`/quiz/${id}/addQuestion`)}>
                Add Question
            </button>
            )}
      </div>
      <div className='question-container'>
            {questions.map((question, index) => (
                <div key={index} class="question-card">
                    <div className='question-form'>
                        <h5 className="question-card-title">Q{++index} : {question.questionTitle}</h5>
                        <form>
                            <p className="question-card-text">1: <input type="radio" name="options" value="1"/> {question.options.optionI}</p>
                            <p className="question-card-text">2: <input type="radio" name="options" value="2"/> {question.options.optionII}</p>
                            <p className="question-card-text">3: <input type="radio" name="options" value="3"/> {question.options.optionIII}</p>
                            <p className="question-card-text">4: <input type="radio" name="options" value="4"/> {question.options.optionIV}</p>
                        </form>
                        <br/>
                        <p className="question-card-text">Correct answer: {question.answer}</p>
                    </div>
                        {userRole==="admin" && (
                        <div className='question-btns'>
                        <button className='ques-update-btn' onClick={()=> navigate(`/quiz/${id}/updateQuestion/${question.questionId}`)}>Update</button>
                        <button
                            className='ques-delete-btn'
                            onClick={() => 
                                Swal.fire({
                                    title: "Warning",
                                    text: "Delete Question?",
                                    icon: "warning",
                                    confirmButtonText:"delete",
                                    confirmButtonColor:"red",
                                    showCancelButton:true
                                  }).then((result)=>{ if(result.isConfirmed) {
                                    deleteQuestion(question.questionId)}
                                } ) }> Delete </button>
                        </div>)}
                    </div>
                ))}
        </div>
    </div>
  )
}

export default ListQuestion
