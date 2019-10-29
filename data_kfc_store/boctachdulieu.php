<?php
	$content = file_get_contents("https://kfcvietnam.com.vn/vn/khuyen-mai.html");
	$patern = '#<div class="col-md-6 block-items">(.*)</div>\s*</div>#imsU';
	preg_match_all($patern,$content,$matches);
	$resurt = array();
	foreach($matches[0] as $key => $value){
		$patern = '#href="(.*)"#imsU';
		preg_match($patern,$value,$link);
		$resurt[$key]['link'] = $link[1];
		//
		$patern = '#src="(.*)"#imsU';
		preg_match($patern,$value,$image);
		$resurt[$key]['image'] = $image[1];
		//$patern = '#class="title-item">(.*)</p>#imsU';
		$patern = '#<p class="title-item"><a .*>(.*)</a></p>#imsU';
		preg_match($patern,$value,$title);
		$resurt[$key]['title'] =$title[1];
		//
		$patern = '#<p class="text-item">(.*)</p>#imsU';
		preg_match($patern,$value,$description);
		$resurt[$key]['description'] = trim($description[1]);
		//
		$patern = '#class="like">(.*)</a>#imsU';
		preg_match($patern,$value,$like);
		$resurt[$key]['like'] = trim($like[1]);
	}
	echo json_encode($resurt);
?>