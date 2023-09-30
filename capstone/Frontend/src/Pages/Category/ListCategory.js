import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Swal from "sweetalert2";
import "./Category.css";
import CategoryService from "../../Services/CategoryService";
import Navbar from "../../Components/Navbar/Navbar";

const ListCategory = () => {
  const userRole = localStorage.getItem("role");

  const navigate = useNavigate();
  const [categories, setCategories] = useState([]);

  useEffect(() => {
    getAllCategories();
  }, []);

  const getAllCategories = () => {
    CategoryService.getAllCategories()
      .then((response) => {
        setCategories(response.data);
      })
      .catch((error) => {
      });
  };

  const deleteCategory = (categoryId) => {
    CategoryService.deleteCategory(categoryId)
      .then((response) => {
        Swal.fire({
          title: "Success",
          text: "Category deleted successfully",
          icon: "success",
          timer: 2000,
          showConfirmButton: false,
        });
        getAllCategories();
      })
      .catch((error) => {
      });
  };

  return (
    <div className="page-container">
      <Navbar />
      <div className="table-container">
        <div className="cat-header">
          <h1>ALL CATEGORIES</h1>
          {userRole === "admin" && (
            <button onClick={() => navigate(`/categories/add`)}>
              Add Category
            </button>
          )}
        </div>
        <table className="category-table">
          <thead>
            <tr>
              <th>Category Id</th>
              <th>Category title</th>
              <th>Category Description</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {categories.map((category, index) => (
              <tr key={index}>
                <td>{++index}</td>
                <td>{category.categoryTitle}</td>
                <td>{category.categoryDescription}</td>
                <td>
                  <button
                    className="action-buttons view-button"
                    onClick={() =>
                      navigate(`/categories/${category.categoryId}/quizzes`)
                    }
                  >
                    View Quiz
                  </button>
                  {userRole === "admin" && (
                    <>
                      <button
                        className="action-buttons update-button"
                        onClick={() =>
                          navigate(`/categories/update/${category.categoryId}`)
                        }
                      >
                        Update
                      </button>
                      <button
                        className="action-buttons delete-button"
                        onClick={() =>
                          Swal.fire({
                            title: "Warning",
                            text: "Delete Category?",
                            icon: "warning",
                            confirmButtonText: "Delete",
                            confirmButtonColor: "red",
                            showCancelButton: true,
                          }).then((result) => {
                            if (result.isConfirmed) {
                              deleteCategory(category.categoryId);
                            }
                          })
                        }
                      >
                        {" "}
                        Delete{" "}
                      </button>
                    </>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ListCategory;
