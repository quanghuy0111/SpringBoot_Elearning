
//Load ra danh sách user
function loadUser() {
    let role ="";
    axios({
        url: 'http://localhost:8080/api/admin/user',
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            //  Lấy ra mảng role
            let arrUser = resp.data;
            // Tạo danh sách thẻ option
            let userTable = document.getElementById("tableUser").getElementsByTagName("tbody")[0];
            let userRow = "";
            for (let userDto of arrUser) {
                userRow += `      <tr>
                <th>${userDto.id}</th>
                <td>${userDto.fullname}</td>
                <td>${userDto.email}</td>
                <td>${userDto.roleDesc}</td>
                <td>
                    <a href="./user-edit.html?id=${userDto.id}" class="btn btn-sm btn-info btn-round py-1 font-weight-bold">Sửa</a>
                    <button type="button" class="btn btn-sm btn-danger btn-round py-1 font-weight-bold" onclick="deleteUser(${userDto.id})">Xóa</button>
                </td>
            </tr>
                
                `;
            }
            
           userTable.innerHTML = userRow;
           
        })
        .catch(function (err) {
            location.replace("/403.html");
            console.log(err.response);
        })
}

//Xóa user
function deleteUser(id){


    axios({
        url: `http://localhost:8080/api/admin/user/${id}`,
        method: 'DELETE',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            //Lấy ra mảng role
            loadUser();
       
        })
        .catch(function (err) {
            console.log(err.response);

        })
}

function logout(){
    localStorage.removeItem('USER_TOKEN');
    location.replace("/login.html");
}
loadUser();