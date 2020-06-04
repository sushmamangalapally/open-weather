<template>
    <!-- <div class="container">
        <p>fdfd</p>
    </div> -->
    <figure class="highcharts-figure">
  <div id="container"></div>
    </figure>
</template>

<script>
var Highcharts = require('highcharts');

export default {
    name: "Chart",
    props: ['forecastObj'],
    methods: { 
        /*
    initSeries : function() {
      return [{
              name: 'Tokyo',
              data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
          }, {
              name: 'New York',
              data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
          }, {
              name: 'Berlin',
              data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0]
          }, {
              name: 'London',
              data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
          }];
    }
*/
    },
      computed : {
          
  },

    mounted : function() {
        // eslint-disable-next-line no-console
        console.log("this.forecastObj")
        // eslint-disable-next-line no-console
        console.log(this.props.forecastObj)
  let averages = [
    [1246406400000, 21.5],
    [1246492800000, 22.1],
    [1246579200000, 23],
    [1246665600000, 23.8],
    [1246752000000, 21.4],
    [1246838400000, 21.3],
    [1246924800000, 18.3],
    [1247011200000, 15.4],
    [1247097600000, 16.4],
    [1247184000000, 17.7],
    [1247270400000, 17.5],
    [1247356800000, 17.6],
    [1247443200000, 17.7],
    [1247529600000, 16.8],
    [1247616000000, 17.7],
    [1247702400000, 16.3],
    [1247788800000, 17.8],
    [1247875200000, 18.1],
    [1247961600000, 17.2],
    [1248048000000, 14.4],
    [1248134400000, 13.7],
    [1248220800000, 15.7],
    [1248307200000, 14.6],
    [1248393600000, 15.3],
    [1248480000000, 15.3],
    [1248566400000, 15.8],
    [1248652800000, 15.2],
    [1248739200000, 14.8],
    [1248825600000, 14.4],
    [1248912000000, 15],
    [1248998400000, 13.6]
  ];


Highcharts.chart('container', {

  title: {
    text: 'July temperatures'
  },

  xAxis: {
    type: 'datetime',
    accessibility: {
      rangeDescription: 'Range: Jul 1st 2009 to Jul 31st 2009.'
    }
  },

  yAxis: {
    title: {
      text: null
    }
  },

  tooltip: {
    crosshairs: true,
    shared: true,
    valueSuffix: '°C'
  },

  series: [{
    name: 'Temperature',
    data: averages,
    zIndex: 1,
    marker: {
      fillColor: 'white',
      lineWidth: 2,
      lineColor: Highcharts.getOptions().colors[0]
    }
  }]
});
    //   this.target = Highcharts.chart(this.$el, {
    //     title: {
    //       text: 'Monthly Average Temperature',
    //       x: -20 //center
    //     },
    //     subtitle: {
    //       text: 'Source: WorldClimate.com',
    //       x: -20
    //     },
    //     xAxis: {
    //       categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
    //       'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
    //     },
    //     yAxis: {
    //       title: {
    //       text: 'Temperature (°C)'
    //     },
    //     plotLines: [{
    //       value: 0,
    //       width: 1,
    //       color: '#808080'
    //     }]
    //   },
    //   tooltip: {
    //     valueSuffix: '°C'
    //   },
    //   legend: {
    //     layout: 'vertical',
    //     align: 'right',
    //     verticalAlign: 'middle',
    //         borderWidth: 0
    //   },
    //   series: this.initSeries()
    // });
  },
  beforeDestroy: function() {
    // this.target.destroy();
  },

}
</script>

<style scoped>

</style>