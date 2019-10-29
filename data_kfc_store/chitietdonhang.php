<?php
	include "connect.php";
	$json =$_POST['json'];
	$data = json_decode($json,true);
	foreach($data as $value){
		$MaDonHang = $value['MaDonHang'];
		$MaSanPham = $value['MaSanPham'];
		$TenSanPham= $value['TenSanPham'];
		$GiaSanPham = $value['GiaSanPham'];
		$SoLuongSanPham= $value['SoLuongSanPham'];
		$query = "INSERT INTO chitetdonhang(ID_ChiTietDonHang,MaDonHang,MaSanPham,TenSanPham,GiaSanPham,SoLuongSanPham) 
		VALUES (null,'$MaDonHang','$MaSanPham','$TenSanPham','$GiaSanPham','$SoLuongSanPham')";
		$Dta = mysqli_query($connection,$query);
	}
	if($Dta){
		echo "1";
	}else{
		echo "0";
	}
?>