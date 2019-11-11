<?php

namespace App\Http\Controllers\API;

use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\BaiHat;
class BaiHatController extends Controller
{
    public function layDanhSach(Request $request)
    {
    	# $page	= $request->query('page', 1);
    	# $limit 	= $request->query('limit', 15);
    	
        // Viết code xử lý
        $page	= $request->query('page', 1);
        $limit 	= $request->query('limit', 15);
        $danhSachs = BaiHat::orderby("tieu_de","asc")->skip(($page-1)*$limit)->take($limit)->get();
        $result = [
            'success'=>true,
            'data'=>[
                'total'=>BaiHat::count(),
                'ds_bai_hat'=>$danhSachs
            ]
            ];
        return response()->json($result);
    }

    public function layBaiHat(Request $request, $id)
    {
        // Viết code xử lý
        $baiHat = BaiHat::find($id);
        $result = [
            'success'=>true,
            'bai_hat'=>$baiHat
            ];
        return response()->json($result);
    }
}
