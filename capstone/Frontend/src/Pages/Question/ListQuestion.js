import React, { useEffect, useState } from 'react'
import QuestionService from '../../Services/QuestionService';
import { useNavigate } from 'react-router-dom';
import Navbar from '../../Components/Navbar/Navbar';
import './Question.css'

const ListQuestion = () => {
    const [questions, setQuestions] = useState([]);
    const navigate = useNavigate();
    const userRole = localStorage.getItem("role");

    useEffect(()=>{
        getAllQuestions();
    },[]);

    const getAllQuestions =() =>{
        QuestionService.getAllQuestions().then((response)=>{
            console.log(response.data);
            setQuestions(response.data)
        })
        .catch((error) => {
            console.log(error);
        })
    }

  return (
    <div>
        <Navbar/>
      <div className='question-container'>
        <div className='question-bg'>
        <h1>QUIZ TIMER</h1>
            {questions.map((question) => (
                <div key={question.questionId} class="question-card">
                    <div className='question-form'>
                        <h5 className="question-card-title">Q: {question.questionTitle}</h5>
                        <form>
                            <p className="question-card-text">1: <input type="radio" name="answer" value="1"/> {question.options.optionI}</p>
                            <p className="question-card-text">2: <input type="radio" name="answer" value="2"/> {question.options.optionII}</p>
                            <p className="question-card-text">3: <input type="radio" name="answer" value="3"/> {question.options.optionIII}</p>
                            <p className="question-card-text">4: <input type="radio" name="answer" value="4"/> {question.options.optionIV}</p>
                        </form>
                    </div>
                        {userRole==="admin" && (
                        <div className='question-btns'>
                        <button className='ques-update-btn'>Update</button>
                        <button
                            className='ques-delete-btn'> Delete </button>
                        </div>)}
                    </div>
                ))}
        </div>
    </div>
    </div>
  )
}

export default ListQuestion
