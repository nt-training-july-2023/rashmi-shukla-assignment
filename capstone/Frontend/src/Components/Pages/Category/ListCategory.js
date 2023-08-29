import React, { useEffect, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import './Category.css';
import CategoryService from '../../../Services/CategoryService';

const ListCategory = () => {
    const navigate = useNavigate();
    useEffect(() => {
        window.history.pushState(null, '', '/ListCategory');
        window.addEventListener('popstate', () => {
          window.history.pushState(null, '', '/ListCategory');
        });
      }, []);

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
    <div>
      <div className='table-container'>
      <h2>List of Categories</h2>
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
                {categories.map((category) => (
                    <tr key={category.categoryId}>
                        <td>{category.categoryId}</td>
                        <td>{category.categoryTitle}</td>
                        <td>{category.categoryDescription}</td>
                        <td>
                            <button onClick={()=> navigate(`/UpdateCategory/${category.categoryId}`)}>Update</button>
                            <button onClick={()=>deleteCategory(category.categoryId)}>Delete</button>
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>
        <Link to="/AddCategory" className='add-category-link'>Add Category</Link>
      </div>
    </div>
  )
}

export default ListCategory
