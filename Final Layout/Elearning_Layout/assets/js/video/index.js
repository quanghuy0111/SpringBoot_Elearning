function loadVideo() {
    axios({
        url: 'http://localhost:8080/api/admin/video',
        method: 'GET',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            //  Lấy ra mảng role
            let arrVideo = resp.data;
            // Tạo danh sách thẻ option
            let videoTable = document.getElementById("tableVideo").getElementsByTagName("tbody")[0];
            let videoRow = "";
            for (let videoDto of arrVideo) {
                videoRow += `      <tr>
                <th>${videoDto.id}</t>
                <td>${videoDto.title}</td>
                <td><img src="http://localhost:8080/video/${videoDto.image}"></td>
                <td>${videoDto.timeCount} phút</td>
                
                <td>
                    <a href="./video-edit.html?id=${videoDto.id}" class="btn btn-sm btn-info btn-round py-1 font-weight-bold">Sửa</a>
                    <button type="button" class="btn btn-sm btn-danger btn-round py-1 font-weight-bold" onclick="deleteVideo(${videoDto.id})">Xóa</button>
                </td>
            </tr>
                
                `;
            }
            
            videoTable.innerHTML = videoRow;
        })
        .catch(function (err) {
            location.replace("/403.html");
            console.log(err.response);
        })
}

function deleteVideo(id){

    axios({
        url: `http://localhost:8080/api/admin/video/${id}`,
        method: 'DELETE',
        headers: {
            Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
          }
    })
        .then(function (resp) {
            //Lấy ra mảng role
            loadVideo();
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

loadVideo();