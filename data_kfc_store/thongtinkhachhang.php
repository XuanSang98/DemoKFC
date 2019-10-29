<?php
	include "connect.php";
	$tenkhachhang =$_POST['tenkhachhang'];
	$sodienthoai =$_POST['sodienthoai'];
	$email =$_POST['email'];
	if(strlen($tenkhachhang)>0 && strlen($email)>0 && strlen($sodienthoai)>0){
		$query = "INSERT INTO donhang(ID_DonHang,TenKhachHang,SoDienThoai,Email) VALUES (null,'$tenkhachhang','$sodienthoai','$email')";
		if(mysqli_query($connection,$query)){
			$iddonhang =$connection->insert_id;
			echo$iddonhang;
		}else{
			echo "That Bai";
		}
	}else{
		echo "Ban Hay Kiem Tra Lai Cac Du Lieu";
	}
?>