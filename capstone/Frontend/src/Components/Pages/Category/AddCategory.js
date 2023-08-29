import React, { useEffect, useState } from 'react'
import './Category.css'
import CategoryService from '../../../Services/CategoryService';
import { Link, useNavigate, useParams } from 'react-router-dom';

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
  },[])

  const heading =() =>{
    if(id) {
      return <h2>Update Category</h2>
    }else{
      return <h2>Add Category</h2>
    }
  }

  return (
    <div className='cat-container'>
      <div>{heading()}
      <form className='cat-form'>
        <div>
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
        </div>
        <div>
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
        </div>
        <div>
          <button onClick={(e)=>saveCategory(e)}  className='cat-button'>
            Submit
          </button>
          <Link to="/ListCategory">
            <button>Cancel</button>
          </Link>
        </div>
        
      </form>
    </div>
  </div>
  )
}

export default AddCategory
