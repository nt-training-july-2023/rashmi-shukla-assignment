import React from 'react'

export default function Form() {
  return (
    <form action="#" className="form">
        <div className="column">
            <div className="input-box">
                <label>Full Name</label>
                <input type="text" placeholder="Enter full name" required />
            </div>
            <div className="input-box">
                <label>User Name</label>
                <input type="text" placeholder="Enter your user-name" required />
              </div>
        </div>

        <div className="column">
            <div className="input-box">
                <label>Email Address</label>
                <input type="email" placeholder="Enter email address" required />
              </div>
            <div className="input-box">
                <label>Phone Number</label>
                <input type="number" placeholder="Enter phone number" required />
            </div>
        </div>


        <div className="column">
            <div className="input-box">
                <label>Password</label>
                <input type="password" placeholder="Enter your Password" required />
              </div>
            <div className="input-box">
                <label>Confirm Password</label>
                <input type="password" placeholder="Confirm your Password" required />
            </div>
        </div>

        <div className="gender-box">
            <h3>Gender</h3>
            <div className="gender-option">
              <div className="gender">
                <input type="radio" id="check-male" name="gender" checked />
                <label for="check-male">Male</label>
              </div>
              <div className="gender">
                <input type="radio" id="check-female" name="gender" />
                <label for="check-female">Female</label>
              </div>
              <div className="gender">
                <input type="radio" id="check-other" name="gender" />
                <label for="check-other">Prefer not to say</label>
              </div>
            </div>
        </div>
        
        <button>Register</button>
        
    </form>
  )
}
