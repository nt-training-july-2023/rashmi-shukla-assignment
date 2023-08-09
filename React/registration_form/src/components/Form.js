import React, { useState } from 'react'

export default function Form() {
  const [fullName, setFullName] = useState('xyz');
  const [userName, setUserName] = useState('xyz12');
  const [email, setEmail] = useState('xyz@gmail.com');
  const [phone, setPhone] = useState('9145000000');
  const [password, setPassword] = useState('*****');
  const [confirmPass, setConfirmPass] = useState('*****');
  const [gender, setGender] = useState('male');

  const handleSubmit= (e) =>{
    e.preventDefault();
    console.log(fullName+" "+userName+" "+email+" "+phone+" "+password+" "+confirmPass+" "+gender);
  }
  return (
    <form  onSubmit={handleSubmit} className="form">
        <div className="column">
            <div className="input-box">
                <label>Full Name</label>
                <input type="text" onChange={(e)=>{setFullName(e.target.value);}} value={fullName} placeholder="Enter full name" required />
            </div>
            <div className="input-box">
                <label>User Name</label>
                <input type="text" onChange={(e)=>{setUserName(e.target.value);}} value={userName} placeholder="Enter your user-name" required />
              </div>
        </div>

        <div className="column">
            <div className="input-box">
                <label>Email Address</label>
                <input type="email" onChange={(e)=>{setEmail(e.target.value);}}  value={email} placeholder="Enter email address" required />
              </div>
            <div className="input-box">
                <label>Phone Number</label>
                <input type="number" onChange={(e)=>{setPhone(e.target.value);}} value={phone} placeholder="Enter phone number" required />
            </div>
        </div>


        <div className="column">
            <div className="input-box">
                <label>Password</label>
                <input type="password" onChange={(e)=>{setPassword(e.target.value);}} value={password} placeholder="Enter your Password" required />
              </div>
            <div className="input-box">
                <label>Confirm Password</label>
                <input type="password" onChange={(e)=>{setConfirmPass(e.target.value);}} value={confirmPass}  placeholder="Confirm your Password" required />
            </div>
        </div>

        <div className="gender-box">
            <h3>Gender</h3>
            <div className="gender-option">
              <div className="gender">
                <input type="radio" id="check-male"  name="gender" value='Male' onChange={(e)=>{setGender(e.target.value);}} />
                <label htmlFor="check-male">Male</label>
              </div>
              <div className="gender">
                <input type="radio" id="check-female"  name="gender" value='Female' onChange={(e)=>{setGender(e.target.value);}} />
                <label htmlFor="check-female">Female</label>
              </div>
              <div className="gender">
                <input type="radio" id="check-other" name="gender" value='prefer not to say' onChange={(e)=>{setGender(e.target.value);}}/>
                <label htmlFor="check-other">Prefer not to say</label>
              </div>
            </div>
        </div>
        
        <button>Register</button>
        
    </form>
  )
}
