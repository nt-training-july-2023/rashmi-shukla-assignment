import React, { useEffect, useState } from 'react'
import './Category.css'
import Swal from 'sweetalert2';
import CategoryService from '../../Services/CategoryService';
import { Link, useNavigate, useParams } from 'react-router-dom';
import Navbar from '../../Components/Navbar/Navbar';
import ErrorPage from '../ErrorPage/ErrorPage'

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
          Swal.fire({
            title: "Success",
            text: "Category updated successfully",
            icon: "success",
            timer:2000,
            showConfirmButton: false,
          });
          navigate("/ListCategory")
        }).catch(error => {
            console.log(error);
            const submitError =error.response.data.message
            Swal.fire({
              title: "Error",
              text: `${submitError}`,
              icon: "error",
              confirmButtonText:"Retry",
              confirmButtonColor:"red"
            });
        });
      }
    else{
      CategoryService.addCategory(category).then((response)=>{
        console.log(response.data);
        Swal.fire({
          title: "Success",
          text: "Category added successfully",
          icon: "success",
          timer:2000,
          showConfirmButton: false,
        });
        navigate("/ListCategory")
      }).catch(error =>{
        const submitError =error.response.data.message
        Swal.fire({
          title: "Error",
          text: `${submitError}`,
          icon: "error",
          confirmButtonText:"Retry",
          confirmButtonColor:"red"
        });
        console.log(error);
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

  const userRole = localStorage.getItem('role');
  if (userRole !== 'admin') {
    return (
        <ErrorPage/>
      );
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
          />
          <label>Category Description </label>
          <input
            type="text"
            value={categoryDescription}
            onChange={(e) => {
              setCategoryDescription(e.target.value);
              setErrors('');
            }}
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
