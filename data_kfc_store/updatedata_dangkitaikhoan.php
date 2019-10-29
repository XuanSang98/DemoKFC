<?php
	require "connect.php";
	$ID_DangKiTaiKhoan =$_POST['id'];
	$Email =$_POST['email'];
	$Mat_Khau =$_POST['matkhau'];
	$Nhap_Lai_Mat_Khau =$_POST['nhaplaimatkhau'];
	$Ten_Nguoi_Dung =$_POST['tennguoidung'];
	$SoDienThoai =$_POST['sodienthoai'];
	$DiaChi =$_POST['diachi'];
	$query = "UPDATE dangki_taikhoan SET Email ='$Email',Mat_Khau ='$Mat_Khau',Nhap_Lai_Mat_Khau='$Nhap_Lai_Mat_Khau',Ten_Nguoi_Dung = '$Ten_Nguoi_Dung',SoDienThoai='$SoDienThoai',DiaChi='$DiaChi'
	WHERE ID_DangKiTaiKhoan = '$ID_DangKiTaiKhoan'";
	if(mysqli_query($connection,$query)){
		echo "Thanh Cong";
	}else
	{
		echo "Loi";
	}
?>