import React, { useEffect, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';
import './Category.css';
import CategoryService from '../../Services/CategoryService';
import Navbar from '../../Components/Navbar/Navbar';

const ListCategory = () => {
    const [valid, setValid] = useState("");
    const result = localStorage.getItem("role");
    const IsLoggedIn = localStorage.getItem("IsLoggedIn");

    useEffect(() => {
        if (result === "admin") {
      setValid("true");
        } else {
      setValid("false");
    }});

    const navigate = useNavigate();
    const[categories, setCategories] = useState([]);

    

    useEffect(()=>{
        getAllCategories();
    },[]);

    const getAllCategories =() =>{
        CategoryService.getAllCategories().then((response)=>{
            setCategories(response.data)
            console.log(response.data)
        })
        .catch((error) => {
            console.log(error);
        })
    }

    const deleteCategory = (categoryId) =>{
        CategoryService.deleteCategory(categoryId).then((response) =>{
            Swal.fire({
                title: "Success",
                text: "Category deleted successfully",
                icon: "success",
                timer:2000,
                showConfirmButton:false
              });
            getAllCategories();
        })
        .catch((error) => {
            console.log(error);
        });
    }

    return (
    <>
        {(IsLoggedIn !== null) ? (
        <div className='page-container'>
        <Navbar/>
      <div className='table-container'>
      {(valid==="true" && IsLoggedIn === "200") && (
      <Link to="/AddCategory" className='add-category-link'>Add Category</Link> )}
        <table className='category-table'>
            <thead>
                <tr>
                    <th>Category Id</th>
                    <th>Category title</th>
                    <th>Category Description</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {categories.map((category,index) => (
                    <tr key={index}>
                        <td>{++index}</td>
                        <td>{category.categoryTitle}</td>
                        <td>{category.categoryDescription}</td>
                        <td>
                            {(valid==="true" && IsLoggedIn === "200") ? (
                            <>
                            <button className='action-buttons update-button' onClick={()=> navigate(`/UpdateCategory/${category.categoryId}`)}>Update</button>
                            <button className='action-buttons delete-button' onClick={()=>
                                Swal.fire({
                                    title: "Warning",
                                    text: "Delete Category",
                                    icon: "warning",
                                    confirmButtonText:"delete",
                                    confirmButtonColor:"red",
                                    showCancelButton:true
                                  }).then((result)=>{ if(result.isConfirmed) {
                                    deleteCategory(category.categoryId)}
                                } ) }> Delete </button>
                            </>
                            ): (
                            <button className='action-buttons delete-button' >View</button>
                            )}
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>
      </div>
      </div>
    ):(navigate('/error-page'))}
    </>
  )
}

export default ListCategory
