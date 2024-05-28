function a() {
  Java.perform(function() {
    var i = Java.use("com.amap.api.location.AMapLocation");
    i.getLatitude.implementation = function () {
      // console.log('b.arg1 = ' + ByteString.of(arg1).hex())
      // console.log('arg2 = ' + arg2)
      var ret = this.getLatitude()
      console.log('getLatitude = ' + ret)
      return 25.013183
    }
  });

}


function b() {
  Java.perform(function() {
    var i = Java.use("com.amap.api.location.AMapLocation");
    i.getLongitude.implementation = function () {
      // console.log('b.arg1 = ' + ByteString.of(arg1).hex())
      // console.log('arg2 = ' + arg2)
      var ret = this.getLongitude()
      console.log('getLongitude = ' + ret)
      return 118.780378
    }
  });

}


function main() {
    a()
    b()
}

main()