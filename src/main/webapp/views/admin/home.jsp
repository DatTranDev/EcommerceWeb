<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/tagLib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<!-- Begin Page Content -->
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">Thống kê</h1>
        <div class="d-flex">
            <input type="number" id="yearInput" placeholder="Nhập năm" class="mr-2">
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
                            <p id="list" style="display: none">${successShopOrderList}</p>
                            <p class="text-xs font-weight-bold text-primary text-uppercase mb-1" id="month-month">
                                Doanh thu Tháng 1
                            </p>
                            <p class="h5 mb-0 font-weight-bold text-gray-800" id="month-revenue">${sum} VND</p>
                        </div>
                        <div class="col-auto">
                            <i id="calendar-icon" class="fas fa-calendar fa-2x text-gray-300" style="cursor: pointer;"></i>
                            <div id="dropdown-menu" class="dropdown-menu">
                                <a class="dropdown-item" href="#" id="t1">Tháng 1</a>
                                <a class="dropdown-item" href="#" id="t2">Tháng 2</a>
                                <a class="dropdown-item" href="#" id="t3">Tháng 3</a>
                                <a class="dropdown-item" href="#" id="t4">Tháng 4</a>
                                <a class="dropdown-item" href="#" id="t5">Tháng 5</a>
                                <a class="dropdown-item" href="#" id="t6">Tháng 6</a>
                                <a class="dropdown-item" href="#" id="t7">Tháng 7</a>
                                <a class="dropdown-item" href="#" id="t8">Tháng 8</a>
                                <a class="dropdown-item" href="#" id="t9">Tháng 9</a>
                                <a class="dropdown-item" href="#" id="t10">Tháng 10</a>
                                <a class="dropdown-item" href="#" id="t11">Tháng 11</a>
                                <a class="dropdown-item" href="#" id="t12">Tháng 12</a>
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
                                Doanh thu trong năm
                            </div>
                            <p class="h5 mb-0 font-weight-bold text-gray-800" id="year-revenue">${sum} VND</p>
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
    document.addEventListener('DOMContentLoaded', function(){
        //Set ngay hien tai
        var currentYear = new Date().getFullYear();
        const yearBOX = document.getElementById('yearInput');
        yearBOX.value = currentYear;

        console.log('loaded1')
        //Lay doanh thu thang
        function getMonthlyRevenues() {
            // Initialize an array with 12 zeros
            var monthlyRevenues = new Array(12).fill(0);

            // Iterate over the list of successful orders
            for (var i = 0; i < listShopOrderSuccess.length; i++) {
                // Get the month and year of the order
                var orderDate = new Date(listShopOrderSuccess[i].orderDate);
                var orderMonth = orderDate.getMonth(); // Note: months are 0-indexed
                var orderYear = orderDate.getFullYear();

                // If the order was made in the current year, add its total to the corresponding month
                if (orderYear == yearBOX.value) {
                    monthlyRevenues[orderMonth] += listShopOrderSuccess[i].orderTotal;
                }
            }

            return monthlyRevenues;
        }

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
        const listShopOrderSuccess = JSON.parse(`${successShopOrderList}`);

        console.log(listShopOrderSuccess);

        const revenues = getMonthlyRevenues();
        var currentMonth = new Date().getMonth();

        const monthBOX = document.getElementById('month-revenue');
        const monthMonth = document.getElementById('month-month');

        monthMonth.textContent = 'Doanh thu Tháng ' + (currentMonth  + 1);
        monthBOX.textContent = revenues[currentMonth].toLocaleString() + ' VND';

        const yearRevenue = document.getElementById('year-revenue');
        yearRevenue.textContent = revenues.reduce((a, b) => a + b, 0).toLocaleString() + ' VND';

        //Them su kien chon thang
        // Get all dropdown items
        var dropdownItems = document.querySelectorAll('.dropdown-item');

        for (var i = 0; i < dropdownItems.length; i++) {
            dropdownItems[i].addEventListener('click', function(event) {
                // Prevent the default action
                event.preventDefault();

                // Get the clicked month
                var clickedMonth = event.target.id.slice(1); // Remove the 't' prefix

                // Update monthMonth and monthBOX
                newrevenues = getMonthlyRevenues();
                monthMonth.textContent = 'Doanh thu Tháng ' + clickedMonth;
                monthBOX.textContent = newrevenues[parseInt(clickedMonth) - 1].toLocaleString() + ' VND';
            });
        }

        // Thêm sự kiện lắng nghe khi người dùng thay đổi giá trị trong input `yearInput`
        document.getElementById('yearInput').addEventListener('change', function() {
            // Lấy giá trị năm từ input `yearInput`
            var year = parseInt(this.value);
            // Lặp qua danh sách `successShopOrderList` và tính tổng doanh thu của các đơn hàng trong năm
            let annualRevenue = 0;
            for (var i = 0; i < listShopOrderSuccess.length; i++) {
                var orderYear = new Date(listShopOrderSuccess[i].orderDate).getFullYear();
                if (orderYear == year) {
                    annualRevenue += listShopOrderSuccess[i].orderTotal;
                    console.log(annualRevenue)
                }
            }

            // Cập nhật giá trị của `Doanh thu (Hàng năm)` trên giao diện
            document.getElementById('year-revenue').textContent = annualRevenue.toLocaleString() + ' VND';


            // Get the new monthly revenues for the selected year
            // Update the data of the line chart
            myLineChart.data.datasets[0].data = getMonthlyRevenues();

            // Redraw the chart with the new data
            myLineChart.update();

            monthBOX.textContent = getMonthlyRevenues()[parseInt(monthMonth.textContent.slice(10))].toLocaleString() + ' VND';
            ;
        });

        // Set new default font family and font color to mimic Bootstrap's default styling
        Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
        Chart.defaults.global.defaultFontColor = '#858796';

        function number_format(number, decimals, dec_point, thousands_sep) {
            // *     example: number_format(1234.56, 2, ',', ' ');
            // *     return: '1 234,56'
            number = (number + '').replace(',', '').replace(' ', '');
            var n = !isFinite(+number) ? 0 : +number,
                prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
                sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
                dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
                s = '',
                toFixedFix = function(n, prec) {
                    var k = Math.pow(10, prec);
                    return '' + Math.round(n * k) / k;
                };
            // Fix for IE parseFloat(0.55).toFixed(0) = 0;
            s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
            if (s[0].length > 3) {
                s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
            }
            if ((s[1] || '').length < prec) {
                s[1] = s[1] || '';
                s[1] += new Array(prec - s[1].length + 1).join('0');
            }
            return s.join(dec);
        }

        // Area Chart Example
        var ctx = document.getElementById("myAreaChart");
        var myLineChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                datasets: [{
                    label: "Doanh thu",
                    lineTension: 0.3,
                    backgroundColor: "rgba(78, 115, 223, 0.05)",
                    borderColor: "rgba(78, 115, 223, 1)",
                    pointRadius: 3,
                    pointBackgroundColor: "rgba(78, 115, 223, 1)",
                    pointBorderColor: "rgba(78, 115, 223, 1)",
                    pointHoverRadius: 3,
                    pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
                    pointHoverBorderColor: "rgba(78, 115, 223, 1)",
                    pointHitRadius: 10,
                    pointBorderWidth: 2,
                    data: getMonthlyRevenues(),
                }],
            },
            options: {
                maintainAspectRatio: false,
                layout: {
                    padding: {
                        left: 10,
                        right: 25,
                        top: 25,
                        bottom: 0
                    }
                },
                scales: {
                    xAxes: [{
                        time: {
                            unit: 'date'
                        },
                        gridLines: {
                            display: false,
                            drawBorder: false
                        },
                        ticks: {
                            maxTicksLimit: 7
                        }
                    }],
                    yAxes: [{
                        ticks: {
                            maxTicksLimit: 5,
                            padding: 10,
                            // Include a dollar sign in the ticks
                            callback: function(value, index, values) {
                                return number_format(value) + 'VND';
                            }
                        },
                        gridLines: {
                            color: "rgb(234, 236, 244)",
                            zeroLineColor: "rgb(234, 236, 244)",
                            drawBorder: false,
                            borderDash: [2],
                            zeroLineBorderDash: [2]
                        }
                    }],
                },
                legend: {
                    display: false
                },
                tooltips: {
                    backgroundColor: "rgb(255,255,255)",
                    bodyFontColor: "#858796",
                    titleMarginBottom: 10,
                    titleFontColor: '#6e707e',
                    titleFontSize: 14,
                    borderColor: '#dddfeb',
                    borderWidth: 1,
                    xPadding: 15,
                    yPadding: 15,
                    displayColors: false,
                    intersect: false,
                    mode: 'index',
                    caretPadding: 10,
                    callbacks: {
                        label: function(tooltipItem, chart) {
                            var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
                            return datasetLabel + ': ' + number_format(tooltipItem.yLabel) + 'VND';
                        }
                    }
                }
            }
        });

        // Pie chart
        // Set new default font family and font color to mimic Bootstrap's default styling
        Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
        Chart.defaults.global.defaultFontColor = '#858796';

// Pie Chart Example
        var ctxg = document.getElementById("myPieChart");
        var myPieChart = new Chart(ctxg, {
            type: 'doughnut',
            data: {
                labels: ["Direct", "Referral", "Social"],
                datasets: [{
                    data: [55, 30, 15],
                    backgroundColor: ['#4e73df', '#1cc88a', '#36b9cc'],
                    hoverBackgroundColor: ['#2e59d9', '#17a673', '#2c9faf'],
                    hoverBorderColor: "rgba(234, 236, 244, 1)",
                }],
            },
            options: {
                maintainAspectRatio: false,
                tooltips: {
                    backgroundColor: "rgb(255,255,255)",
                    bodyFontColor: "#858796",
                    borderColor: '#dddfeb',
                    borderWidth: 1,
                    xPadding: 15,
                    yPadding: 15,
                    displayColors: false,
                    caretPadding: 10,
                },
                legend: {
                    display: false
                },
                cutoutPercentage: 80,
            },
        });

    })
</script>

</body>
</html>
