// 测试，不在 组件中的错误，能不能获取到
// import HttpError from '@/util/exception/HttpError'

(function () {
  setTimeout(() => {
    // throw new HttpError('这个错不在组件中', '800')
    // const a = {}
    // console.log(a.b.c.default)
    throw false
  }, 2000)
})()
