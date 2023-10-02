import React, { useEffect, useState } from 'react'
import Navbar from '../../Components/Navbar/Navbar';
import ResultService from '../../Services/ResultService';
import './Result.css'

const ListResult = () => {
    const [results, setResults] = useState([]);
    const userRole = localStorage.getItem("role");
    const userEmail = localStorage.getItem("userEmail");

    useEffect(() => {
      if(userRole==="admin"){
        getAllResults();
      } else{
        getResultByUserEmail();
      }
    },[])

    const getAllResults = () => {
        ResultService.getAllResults()
          .then((response) => {
            setResults(response.data);
          })
          .catch((error) => {
          });
    };

    const getResultByUserEmail= () => {
      ResultService.getResultByUserEmail(userEmail)
        .then((response) => {
          setResults(response.data);
        })
        .catch((error) => {
        });
    }

  return (
    <div className="res-page-container">
      <Navbar />
      <div className="res-table-container">
        <div className="res-header">
          <h1>ALL RESULTS</h1>
        </div>
        <table className="res-table">
          <thead>
            <tr>
              <th>SNo.</th>
              <th>User Name</th>
              <th>User Email</th>
              <th>Quiz</th>
              <th>Category</th>
              <th>Total Questions</th>
              <th>Attempted Questions</th>
              <th>Obtained Marks</th>
              <th>Total Marks</th>
              <th>Date & Time</th>
            </tr>
          </thead>
          <tbody>
            {results.map((result, index) => (
              <tr key={index}>
                <td>{++index}</td>
                <td>{result.userName}</td>
                <td>{result.userEmail}</td>
                <td>{result.quizTitle}</td>
                <td>{result.categoryTitle}</td>
                <td>{result.totalQuestions}</td>
                <td>{result.attemptedQuestions}</td>
                <td>{result.obtainedMarks}</td>
                <td>{result.totalMarks}</td>
                <td>{result.dateTime}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}

export default ListResult
