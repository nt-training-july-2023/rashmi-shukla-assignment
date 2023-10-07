import React from 'react'

const PageHeader =  ({className, heading, displayButton, onClick, name, isTest, timer,submitted }) => {
  const userRole = localStorage.getItem("role");

  const renderButton = () => (
    <button onClick={onClick}>
      {name}
    </button>
  );

  const renderQuizControls = () => (
    <>
      <p>Time Remaining: {timer}</p>
      <button onClick={onClick} disabled={submitted}>
        Submit Quiz
      </button>
    </>
  );

  return (
    <div className={className}>
      <h1>{heading}</h1>
      {displayButton && userRole === "admin" ? renderButton() : isTest && renderQuizControls()}
    </div>
  )

}

export default PageHeader
