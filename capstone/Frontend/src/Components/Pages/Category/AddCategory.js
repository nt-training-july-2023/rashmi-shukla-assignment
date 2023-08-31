import React, { useEffect, useState } from 'react'
import './Category.css'
import CategoryService from '../../../Services/CategoryService';
import { Link, useNavigate, useParams } from 'react-router-dom';
import Navbar from '../Navbar/Navbar';

const AddCategory = () => {
  const [categoryTitle, setCategoryTitle] = useState('');
  const [categoryDescription, setCategoryDescription] = useState('');
  const [errors, setErrors] = useState('')
  const navigate = useNavigate();
  const {id} = useParams();

  const validateForm =() =>{
    if(categoryTitle==='' || categoryDescription===''){
      setErrors('*all the fields are mandatory')
      return true;
    }
    return false
  }

  const saveCategory=(e)=>{
    e.preventDefault();
    if(!validateForm()){
      const category = {categoryTitle, categoryDescription};
      if(id){
        CategoryService.updateCategory(id, category).then((response)=>{
          console.log(response.data);
          navigate("/ListCategory")
        }).catch(error => {
            console.log(error);
            setErrors(error.response.data.message)
        });
      }
    else{
      CategoryService.addCategory(category).then((response)=>{
        console.log(response.data);
        navigate("/ListCategory")
      }).catch(error =>{
        console.log(error);
        setErrors(error.response.data.message);
      })
    }
  }
}

  useEffect(()=>{
    if(id){
      CategoryService.getCategoryById(id).then((response)=>{
          setCategoryTitle(response.data.categoryTitle)
          setCategoryDescription(response.data.categoryDescription)
      }).catch(error => {
        console.log(error)
      })
    }
  },[id])

  const heading =() =>{
    if(id) {
      return <h2 style={{textAlign:'center'}}>UPDATE CATEGORY</h2>
    }else{
      return <h2 style={{textAlign:'center'}}>ADD CATEGORY</h2>
    }
  }

  return (
    <div className='page-container'>
      <Navbar/>
    <div className='cat-container'>
      <div>{heading()}
      <form className='cat-form'>
          <label>Category Title</label>
          <input
            type="text"
            value={categoryTitle}
            onChange={(e) => {
              setCategoryTitle(e.target.value);
              setErrors('');
            }}
            //required
          />
          <label>Category Description </label>
          <input
            type="text"
            value={categoryDescription}
            onChange={(e) => {
              setCategoryDescription(e.target.value);
              setErrors('');
            }}
            //required
          />
          <span>{errors}</span>
          <button onClick={(e)=>saveCategory(e)}  className='cat-button'>
            Submit
          </button>
          <Link to="/ListCategory">
            <button>Cancel</button>
          </Link>
        
      </form>
    </div>
    </div>
  </div>
  )
}

export default AddCategory
