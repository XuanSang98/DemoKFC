<?php
	require "connect.php";
	$Email =$_POST['email'];
	$Mat_Khau = $_POST['matKhau'];
	$Nhap_Lai_Mat_Khau = $_POST['nhapLaiMatKhau'];
	$Ten_Nguoi_Dung = $_POST['tenNguoiDung'];
	$SoDienThoai = $_POST['soDienThoai'];
	$DiaChi = $_POST['diaChi'];
	$query = "INSERT INTO dangki_taikhoan VALUES(null,'$Email','$Mat_Khau','$Nhap_Lai_Mat_Khau','$Ten_Nguoi_Dung','$SoDienThoai','$DiaChi')";
	if(mysqli_query($connection,$query)){
		echo "Thanh Cong";
	}else
	{
		echo "Loi";
	}
?>