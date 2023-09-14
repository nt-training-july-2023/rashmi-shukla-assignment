import React from 'react'
import './PageTitle.css'

const PageTitle =  ({ heading, onClick, name }) => {
  const userRole = localStorage.getItem("role");
    return (
      <div className='page-title'>
        <h1>{heading}</h1>
        {(userRole==="admin") && (
        <button onClick={onClick}>
          {name}
        </button> )}
      </div>
  )
}

export default PageTitle
