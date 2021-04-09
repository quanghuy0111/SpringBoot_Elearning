function loadRole() {
    axios({
        url: 'http://localhost:8080/api/admin/role',
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            //  Lấy ra mảng role
            let arrRole = resp.data;
            // Tạo danh sách thẻ option
            let roleTable = document.getElementById("tableRole").getElementsByTagName("tbody")[0];
            let roleRow = "";
            for (let roleDto of arrRole) {
                roleRow += `      <tr>
                <th>${roleDto.id}</t>
                <td>${roleDto.name}</td>
                <td>${roleDto.description}</td>
            
              
            </tr>
                
                `;
            }
            
           roleTable.innerHTML = roleRow;
        })
        .catch(function (err) {
            location.replace("/403.html");
            console.log(err.response);
        })
}

function logout(){
    localStorage.removeItem('USER_TOKEN');
    location.replace("/login.html");
}

function logout(){
    localStorage.removeItem('USER_TOKEN');
    location.replace("/login.html");
}
loadRole();