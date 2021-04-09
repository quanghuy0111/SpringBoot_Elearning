function loadTarget() {
    axios({
        url: 'http://localhost:8080/api/admin/target',
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            //  Lấy ra mảng role
            let arrTarget = resp.data;
            // Tạo danh sách thẻ option
            let targetTable = document.getElementById("tableTarget").getElementsByTagName("tbody")[0];
            let targetRow = "";
            for (let targetDto of arrTarget) {
                targetRow += `      <tr>
                <th>${targetDto.id}</t>
                <td>${targetDto.title}</td>
                <td>
                    <a href="./target-edit.html?id=${targetDto.id}" class="btn btn-sm btn-info btn-round py-1 font-weight-bold">Sửa</a>
                    <button type="button" class="btn btn-sm btn-danger btn-round py-1 font-weight-bold" onclick="deleteTarget(${targetDto.id})">Xóa</button>
                </td>
            </tr>
                
                `;
            }
            
            targetTable.innerHTML = targetRow;
        })
        .catch(function (err) {
            location.replace("/403.html");
            console.log(err.response);
        })
}

function deleteTarget(id){

    axios({
        url: `http://localhost:8080/api/admin/target/${id}`,
        method: 'DELETE',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            //Lấy ra mảng role
            loadTarget();
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


loadTarget();