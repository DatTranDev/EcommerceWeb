<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/tagLib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin</title>
</head>
<body>
<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Thống kê</h1>
        <div class="d-flex">
            <input type="text" id="yearInput" placeholder="Nhập năm" class="mr-2">
            <a href="#"
               class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                    class="fas fa-download fa-sm text-white-50"></i>Tạo báo cáo</a>
        </div>
    </div>

    <!-- Content Row -->
    <div class="row">

        <!-- Earnings (Monthly) Card Example -->
        <div class="col-xl-6 col-md-6 mb-4">
            <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                Doanh thu (Tháng 1)
                            </div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800">$40,000</div>
                        </div>
                        <div class="col-auto">
                            <i id="calendar-icon" class="fas fa-calendar fa-2x text-gray-300" style="cursor: pointer;"></i>
                            <div id="dropdown-menu" class="dropdown-menu">
                                <a class="dropdown-item" href="#">Tháng 1</a>
                                <a class="dropdown-item" href="#">Tháng 2</a>
                                <a class="dropdown-item" href="#">Tháng 3</a>
                                <a class="dropdown-item" href="#">Tháng 4</a>
                                <a class="dropdown-item" href="#">Tháng 5</a>
                                <a class="dropdown-item" href="#">Tháng 6</a>
                                <a class="dropdown-item" href="#">Tháng 7</a>
                                <a class="dropdown-item" href="#">Tháng 8</a>
                                <a class="dropdown-item" href="#">Tháng 9</a>
                                <a class="dropdown-item" href="#">Tháng 10</a>
                                <a class="dropdown-item" href="#">Tháng 11</a>
                                <a class="dropdown-item" href="#">Tháng 12</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Earnings (Annual) Card Example -->
        <div class="col-xl-6 col-md-6 mb-4">
            <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div
                                    class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                Doanh thu (Hàng năm)
                            </div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800">$215,000</div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Earnings Chart -->
        <div class="col-xl-8 col-lg-7">
            <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div
                        class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="m-0 font-weight-bold text-primary">Tổng quan doanh thu</h6>
                    <div class="dropdown no-arrow">
                        <a class="dropdown-toggle" href="#" role="button"
                           id="dropdownMenuLink" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false"> <i
                                class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                        </a>
                        <div
                                class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                aria-labelledby="dropdownMenuLink">
                            <div class="dropdown-header">Dropdown Header:</div>
                            <a class="dropdown-item" href="#">Action</a> <a
                                class="dropdown-item" href="#">Another action</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Something else here</a>
                        </div>
                    </div>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                    <div class="chart-area">
                        <canvas id="myAreaChart"></canvas>
                    </div>
                </div>
            </div>
        </div>

        <!-- Revenue Sources Pie Chart -->
        <div class="col-xl-4 col-lg-5">
            <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div
                        class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                    <h6 class="m-0 font-weight-bold text-primary">Nguồn doanh thu</h6>
                    <div class="dropdown no-arrow">
                        <a class="dropdown-toggle" href="#" role="button"
                           id="dropdownMenuLinkk" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false"> <i
                                class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                        </a>
                        <div
                                class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                aria-labelledby="dropdownMenuLink">
                            <div class="dropdown-header">Dropdown Header:</div>
                            <a class="dropdown-item" href="#">Action</a> <a
                                class="dropdown-item" href="#">Another action</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Something else here</a>
                        </div>
                    </div>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                    <div class="chart-pie pt-4 pb-2">
                        <canvas id="myPieChart"></canvas>
                    </div>
                    <div class="mt-4 text-center small">
                            <span class="mr-2">
                                <i class="fas fa-circle text-primary"></i> Trực tiếp
                            </span> <span class="mr-2">
                                <i class="fas fa-circle text-success"></i> Xã hội
                            </span> <span class="mr-2">
                                <i class="fas fa-circle text-info"></i> Khác
                            </span>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>


<script>
    document.getElementById('calendar-icon').addEventListener('click', function() {
        var dropdownMenu = document.getElementById('dropdown-menu');
        if (dropdownMenu.style.display === 'none' || dropdownMenu.style.display === '') {
            dropdownMenu.style.display = 'block';
        } else {
            dropdownMenu.style.display = 'none';
        }
    });

    // Đóng menu thả xuống nếu nhấp ra ngoài
    window.onclick = function(event) {
        if (!event.target.matches('#calendar-icon')) {
            var dropdowns = document.getElementsByClassName("dropdown-menu");
            for (var i = 0; i < dropdowns.length; i++) {
                var openDropdown = dropdowns[i];
                if (openDropdown.style.display === 'block') {
                    openDropdown.style.display = 'none';
                }
            }
        }
    }
    // // Thêm sự kiện lắng nghe khi người dùng thay đổi giá trị trong input `yearInput`
    // document.getElementById('yearInput').addEventListener('change', function() {
    //     // Lấy giá trị năm từ input `yearInput`
    //     var year = parseInt(this.value);
    //
    //     // Lặp qua danh sách `successShopOrderList` và tính tổng doanh thu của các đơn hàng trong năm
    //     var annualRevenue = 0;
    //     for (var i = 0; i < successShopOrderList.length; i++) {
    //         var orderYear = new Date(successShopOrderList[i].orderDate).getFullYear();
    //         if (orderYear === year) {
    //             annualRevenue += successShopOrderList[i].totalAmount;
    //         }
    //     }
    //
    //     // Cập nhật giá trị của `Doanh thu (Hàng năm)` trên giao diện
    //     document.querySelector('.text-success.text-gray-800').textContent = "$" + annualRevenue.toLocaleString();
    // });

</script>



</body>
</html>
