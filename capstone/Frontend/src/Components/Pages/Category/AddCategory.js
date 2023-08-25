import React from 'react'

const AddCategory = () => {
  return (
    <div className="Auth-form-container">
    <form className="Auth-form" >
      <div className="Auth-form-content">
        <h3 className="Auth-form-title">ADD CATEGORY</h3>
        <div className="form-group mt-3">
          <label>Category Title</label>
          <input
            type="text"
            className="form-control mt-1"
            required
          />
        </div>
        <div className="form-group mt-3">
          <label>Category Description </label>
          <input
            type="text"
            className="form-control mt-1"
            required
          />
        </div>
        <div className="d-grid gap-2 mt-3">
          <button type="Add"  className="btn btn-primary">
            Submit
          </button>
        </div>
        
      </div>
    </form>
  </div>
  )
}

export default AddCategory
