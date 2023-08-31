import React, { useEffect, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import './Category.css';
import CategoryService from '../../../Services/CategoryService';
import Navbar from '../Navbar/Navbar';

const ListCategory = () => {
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
            getAllCategories();
        })
        .catch((error) => {
            console.log(error);
        });
    }

    return (
        <div className='page-container'>
        <Navbar/>
      <div className='table-container'>
      <Link to="/AddCategory" className='add-category-link'>Add Category</Link>
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
                            <button className='action-buttons update-button' onClick={()=> navigate(`/UpdateCategory/${category.categoryId}`)}>Update</button>
                            <button className='action-buttons delete-button' onClick={()=>deleteCategory(category.categoryId)}>Delete</button>
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>
      </div>
      </div>
  )
}

export default ListCategory
