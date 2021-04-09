
//Hàm load ra category
function loadCategory() {
    axios({
        url: 'http://localhost:8080/api/admin/category',
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            //  Lấy ra mảng role
            let arrCate = resp.data;
            // Tạo danh sách thẻ option
            let categoryTable = document.getElementById("tableCategory").getElementsByTagName("tbody")[0];
            let cateRow = "";
            for (let cateDto of arrCate) {
                cateRow += `      <tr>
                <th>${cateDto.id}</t>
                <td>${cateDto.title}</td>
                <td><img src="http://localhost:8080/category/${cateDto.icon}"></td>
                
                <td>
                    <a href="./category-edit.html?id=${cateDto.id}" class="btn btn-sm btn-info btn-round py-1 font-weight-bold">Sửa</a>
                    <button type="button" class="btn btn-sm btn-danger btn-round py-1 font-weight-bold" onclick="deleteCate(${cateDto.id})">Xóa</button>
                </td>
            </tr>
                
                `;
            }
            
            categoryTable.innerHTML = cateRow;
        })
        .catch(function (err) {
            location.replace("/403.html");
            console.log(err.response);
        })
}

//Hàm xóa Category
function deleteCate(id){

    axios({
        url: `http://localhost:8080/api/admin/category/${id}`,
        method: 'DELETE',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            //Lấy ra mảng role
            loadCategory();
            console.log("xóa thành công");
            
       
        })
        .catch(function (err) {
            console.log(err.response);
        })
}


function logout(){
    localStorage.removeItem('USER_TOKEN');
    location.replace("/login.html");
}

loadCategory();