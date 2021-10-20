import FormValidationError from '@/util/exception/FormValidationError'
import $config from '@/config/config'

import _ from 'lodash'

const $util = {
  /**
   * 整体表单验证的方法，处理了 <el-form> 的 validate promise 化后失败的行为
   * 根据配置，控制要不要默认显示一个信息
   * @param formComp VueComponent
   * @param message string
   * @return {Promise<void>}
   */
  async formValidate(formComp, message) {
    try {
      await formComp.validate()
    } catch (err) {
      if (err === false) { // element-ui 的表单验证错误
        if (message || $config.ifShowFormValidateDefaultMessage) {
          throw new FormValidationError(message || '请完善表单', formComp, true)
        } else {
          throw new FormValidationError('请完善表单', formComp, false)
        }
      } else {
        throw err
      }
    }
  },
  debounce(fn, milliseconds) {
    let timer = 0
    return function () {
      timer && clearTimeout(timer)
      timer = setTimeout(() => {
        fn.apply(this, arguments)
      }, milliseconds)
    }
  },
  /**
   * 节流
   * @param fn
   * @param milliseconds
   * @return {function(...[*]=)}
   */
  throttle(fn, milliseconds) {
    let timer = 0
    return function () {
      if (timer) {
        return
      }
      fn.apply(this, arguments)
      timer = setTimeout(() => {
        timer = 0
      }, milliseconds)
    }
  },
  /**
   * 树转扁平化数据方法
   * @param data []
   * @param fn 参数 item
   * @return []
   */
  treeFlat(data, fn) {
    const result = []
    data.forEach(item => {
      result.push(fn(item))
      if (item.children && item.children.length) {
        result.push(...this.treeFlat(item.children, fn))
      }
    })
    return result
  },
  /**
   * 扁平专属
   * @param data
   * @param fields 字段名
   * @returns {*}
   */
  flatToTree(data, fields = { ID: 'id', PARENT: 'parentId', CHILDREN: 'children' }) {
    if (typeof fields !== 'object') {
      fields = { ID: 'id', PARENT: 'parentId', CHILDREN: 'children' }
    }
    const ID = fields.ID || 'id'
    const PARENT = fields.PARENT || 'parentId'
    const CHILDREN = fields.CHILDREN || 'children'
    const parentIdMap = {
      root: [],
    }
    data.forEach(item => {
      if (!item[PARENT]) {
        parentIdMap['root'].push(item)
        return
      }
      if (!Object.prototype.hasOwnProperty.call(parentIdMap, item[PARENT])) {
        parentIdMap[item[PARENT]] = []
      }
      parentIdMap[item[PARENT]].push(item)
    })
    const result = {}
    this.connector(result, 'root', parentIdMap, { CHILDREN, ID })
    // console.log(result.children)
    return result[CHILDREN]
  },
  connector(result, key, parentMap, { CHILDREN, ID }) {
    if (parentMap[key]) {
      result[CHILDREN] = parentMap[key]
      result[CHILDREN].forEach(item => {
        this.connector(item, item[ID], parentMap, { CHILDREN, ID })
      })
    } else {
      return result
    }
  },
  /**
   * 树 找到某个节点 的方法
   * @param data []
   * @param fn 参数 item
   * @return {*|null|obj}
   */
  treeFind(data, fn) { // treeFind 方法找到某个节点
    for (const item of data) {
      if (fn(item)) { // 找到, 返回节点
        return item
      }
      if (item.children && item.children.length) {
        const tmpResult = this.treeFind(item.children, fn)
        if (tmpResult) {
          return tmpResult
        }
      }
    }
    return null
  },
  treeForEach(data, fn, level) {
    level = 0
    data.forEach(item => {
      fn(item, level)
      if (item.children && item.children.length) {
        this.treeForEach(item.children, fn, level + 1)
      }
    })
  },
  /**
   * 树归并方法
   * @param data 树数据
   * @param fn 迭代器, 参数 (prev, item, index)
   * @param prev 归并初始值, 默认 0
   * @return {*}
   */
  treeReduce(data, fn, prev = 0) {
    data.forEach((item, index) => {
      prev = fn(prev, item, index)
      if (item.children && item.children.length) {
        prev = this.treeReduce(item.children, fn, prev)
      }
    })
    return prev
  },
  /**
   * 转万
   * @param number
   * @return {number}
   */
  to10Thousand(number) {
    return Number(((number) / 10000).toFixed(6))
  },
  /**
   * 判断两个数字是否在误差范围内相等
   * @param num1
   * @param num2
   * @param precision 精度， 10^-precision
   * @return {boolean}
   */
  ifNumberEqual(num1, num2, precision = 6) {
    return Math.abs(num1 - num2) < Math.pow(10, -1 * precision)
  },
  /**
   * 获取欢迎语
   * @return {string}
   */
  getGreetingNote() {
    const hour = new Date().getHours()
    if (hour < 5) {
      return '晚上好'
    } else if (hour >= 5 && hour < 9) {
      return '早上好'
    } else if (hour >= 9 && hour < 11) {
      return '上午好'
    } else if (hour >= 11 && hour < 13) {
      return '中午好'
    } else if (hour >= 13 && hour < 18) {
      return '下午好'
    } else if (hour >= 18) {
      return '晚上好'
    }
  },
  /**
   * 小数位格式化，最终结果 Number
   * @param num
   * @param precision
   * @returns {number}
   */
  toFixedNumber(num, precision = 2) {
    if (typeof num !== 'number' || !num) {
      return 0
    }
    return Number(num.toFixed(precision))
  },
  /**
   * 分页多选逻辑，为 @selection-change
   * @param selected
   * @param selection
   * @param tableData
   * @param prop 获取值，取值的路径
   * @return {*}
   */
  pageSelectedForSelectionChangeCustomProp(selected, selection, tableData, prop = 'id') {
    const _pageSelected = tableData.filter(item => selection.findIndex(selectedItem => _.get(selectedItem, prop) === _.get(item, prop)) > -1)
    const _pageNotSelected = tableData.filter(item => selection.findIndex(selectedItem => _.get(selectedItem, prop) === _.get(item, prop)) === -1)
    _pageSelected.forEach(item => {
      if (selected.indexOf(_.get(item, prop)) === -1) { // 没有，添加进
        selected.push(_.get(item, prop))
      }
    })
    _pageNotSelected.forEach(item => {
      const _index = selected.indexOf(_.get(item, prop))
      if (_index > -1) { // 有，删掉
        selected.splice(_index, 1)
      }
    })
    return selected
  },
  /**
   * 递归获取名字
   * @param obj
   * @param nameArr
   * @param fields
   * @param connector
   * @returns {*}
   */
  getNameRecursive(obj, nameArr = [], fields = { name: 'name', parent: 'parent' }, connector = '/') {
    if (!obj) {
      return nameArr.join(connector)
    } else {
      if (obj.parent) {
        nameArr.push(obj[fields.name])
      }
      return this.getNameRecursive(obj.parent, nameArr, fields, connector)
    }
  },
  /**
   * 清洗外键，防止发送数据过于庞大
   * @param data
   * @param foreignKeyPropList
   */
  cleanForeignKey(data, foreignKeyPropList) {
    foreignKeyPropList.forEach(item => {
      data[item] = data[item] ? { id: data[item].id } : null
    })
  },
  /**
   * 生成 uuid
   * @return {string}
   */
  uuid() {
    let s = []
    let hexDigits = '0123456789abcdef'
    for (let i = 0; i < 36; i++) {
      s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1)
    }
    s[14] = '4'  // bits 12-15 of the time_hi_and_version field to 0010
    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1)  // bits 6-7 of the clock_seq_hi_and_reserved to 01
    s[8] = s[13] = s[18] = s[23] = '-'

    return s.join('')
  },
  /**
   * 日期转汉字
   * @param date yyyy-mm-dd
   */
  CNDateString: function (date) {
    const cn = ['〇', '一', '二', '三', '四', '五', '六', '七', '八', '九']
    const arr = []
    const YY = date.getFullYear().toString()
    for (let i = 0; i < YY.length; i++) {
      if (cn[YY.charAt(i)]) {
        arr.push(cn[YY.charAt(i)])
      }
    }
    arr.push(',')
    const MM = date.getMonth() + 1
    if (MM < 10) {
      arr.push(cn[MM])
    } else if (MM < 20) {
      if (MM === 10) {
        arr.push('十')
      } else {
        arr.push('十' + cn[MM % 10])
      }
    }
    arr.push(',')
    const DD = date.getDate()
    if (DD < 10) {
      arr.push(cn[DD])
    } else if (DD < 20) {
      if (DD === 10) {
        arr.push('十')
      } else {
        arr.push('十' + cn[DD % 10])
      }
    } else if (DD < 30) {
      if (DD === 20) {
        arr.push('二十')
      } else {
        arr.push('二十' + cn[DD % 10])
      }

    } else {
      if (DD === 30) {
        arr.push('三十')
      } else {
        arr.push('三十' + cn[DD % 10])
      }
    }
    return arr.join('').split(',')
  },

  currentMonthFirst() {
    const date = new Date()
    date.setDate(1)
    return date
  },
  currentMonthLast() {
    const date = new Date()
    let currentMonth = date.getMonth()
    const nextMonth = ++currentMonth
    const nextMonthFirstDay = new Date(date.getFullYear(), nextMonth, 1)
    const oneDay = 1000 * 60 * 60 * 24
    return new Date(nextMonthFirstDay - oneDay)
  },
  /**
   * Cookie储存
   * @param cname 存入的name名
   * @param  cvalue 储存的数据
   * @param  exdays 保存的天数
   */
  setCookie: function (cname, cvalue, exdays) {
    const d = new Date()
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000))
    const expires = 'expires=' + d.toUTCString()
    console.info(cname + '=' + cvalue + '; ' + expires)
    document.cookie = cname + '=' + cvalue + '; ' + expires
    console.info(document.cookie)
  },
  /**
   * Cookie获取
   * @param cname 取的name名
   */
  getCookie: function (cname) {
    const name = cname + '='
    const ca = document.cookie.split(';')
    for (let i = 0; i < ca.length; i++) {
      let c = ca[i]
      console.log(c)
      while (c.charAt(0) === ' ') c = c.substring(1)
      if (c.indexOf(name) !== -1) {
        return c.substring(name.length, c.length)
      }
    }
    return ''
  },
  /**
   * localStorage储存
   * @param localName 存入的name名
   * @param  data 储存的数据
   */
  localSetData: function (localName, data) { //储存localStorage
    localStorage.setItem(localName, JSON.stringify(data))
  },
  /**
   * localStorage取出
   * @param localName 取出的name名
   */
  localGetData: function (localName) { //取出localStorage
    return JSON.parse(localStorage.getItem(localName))
  },
  /**
   * sessionStorage储存
   * @param sessionName 存入的name名
   * @param  data 储存的数据
   */
  sessionSetData: function (sessionName, data) {
    data.data['realNameURL'] = encodeURI(data.data.realName)
    sessionStorage.setItem(sessionName, JSON.stringify(data))
  },
  /**
   * sessionStorage取出
   * @param sessionName 取出的name名
   */
  sessionGetData: function (sessionName) {
    return JSON.parse(sessionStorage.getItem(sessionName))
  },
  number_chinese(money) {
    let i
    if (money === 0 || Number(money) === 0 || !money) {
      return '零元整'
    }
    //  将数字金额转换为大写金额
    const cnNums = ['零',
      '壹',
      '贰',
      '叁',
      '肆',
      '伍',
      '陆',
      '柒',
      '捌',
      '玖'] //汉字的数字
    const cnIntRadice = ['', '拾', '佰', '仟'] //基本单位
    const cnIntUnits = ['', '万', '亿', '万亿'] //对应整数部分扩展单位
    const cnDecUnits = ['角', '分', '毫', '厘'] //对应小数部分单位
    const cnInteger = '整' //整数金额时后面跟的字符
    const cnIntLast = '元' //整数完以后的单位
    //最大处理的数字
    const maxNum = 999999999999999.9999
    let integerNum  //金额整数部分
    let decimalNum  //金额小数部分
    //输出的中文金额字符串
    let chineseStr = ''
    // let parts  //分离金额后用的数组，预定义
    if (money === '') {
      return ''
    }

    money = parseFloat(money)
    if (money >= maxNum) {
      //超出最大处理数字
      return '超出最大处理数字'
    }
    if (money === 0) {
      chineseStr = cnNums[0] + cnIntLast + cnInteger
      return chineseStr
    }

    //四舍五入保留两位小数,转换为字符串
    money = Math.round(money * 100).toString()
    integerNum = money.substr(0, money.length - 2)
    decimalNum = money.substr(money.length - 2)

    //获取整型部分转换
    if (parseInt(integerNum, 10) > 0) {
      let zeroCount = 0
      const IntLen = integerNum.length
      for (i = 0; i < IntLen; i++) {
        var n = integerNum.substr(i, 1)
        const p = IntLen - i - 1
        const q = p / 4
        const m = p % 4
        if (n === '0') {
          zeroCount++
        } else {
          if (zeroCount > 0) {
            chineseStr += cnNums[0]
          }
          //归零
          zeroCount = 0
          chineseStr += cnNums[parseInt(n)] + cnIntRadice[m]
        }
        if (m === 0 && zeroCount < 4) {
          chineseStr += cnIntUnits[q]
        }
      }
      chineseStr += cnIntLast
    }
    //小数部分
    if (decimalNum !== '') {
      const decLen = decimalNum.length
      for (let i = 0; i < decLen; i++) {
        let  n = decimalNum.substr(i, 1)
        if (n !== '0') {
          chineseStr += cnNums[Number(n)] + cnDecUnits[i]
        }
      }
    }
    if (chineseStr === '') {
      chineseStr += cnNums[0] + cnIntLast + cnInteger
    } else if (decimalNum === '' || /^0*$/.test(decimalNum)) {
      chineseStr += cnInteger
    }
    return chineseStr
  },
  /**
   * 数字转千分位
   * @param num
   * @param fractionDigits 小数位数
   * @return {string}
   */
  toThousand: function (num, fractionDigits) {
    fractionDigits = fractionDigits || 0
    if (typeof num !== 'number') {
      num = Number(num)
    }
    if (fractionDigits > 0 && num) {
      num = Math.round(num * Math.pow(10, fractionDigits)) / Math.pow(10, fractionDigits)
    }
    const numStr = (num || 0).toString()
    const arr = numStr.split('.')
    let int = arr[0] // 整数部分
    let decimal = arr[1] || 0 // 小数
    // 处理整数
    const pattern = /(?=(?!(\b))(\d{3})+$)/g
    int = int.replace(pattern, ',')
    // 处理小数
    // if fractionDigits = 6 表示万元，小数位数 < 2 时， 格式化到 2 位， 2 - 6 位之间，格式化到 2 - 6 位， 6位以上， 格式化到 6 位
    if (fractionDigits === 6) {
      if (!decimal || decimal.length <= 2) {
        fractionDigits = 2
      } else if (decimal.length <= 6 && decimal.length > 2) {
        fractionDigits = decimal.length
      } else {
        fractionDigits = 6
      }
    }
    decimal = Number('0.' + decimal).toFixed(fractionDigits)
    return decimal === '0' ? int : (int + '.' + decimal.split('.')[1])
  },
  /**
   * 身份证号验证
   * @param rule
   * @param value
   * @param callback
   * @return {*}
   */
  chinaIdentityValid: (rule, value, callback) => {
    if (!value) {
      return callback()
    }
    let city = {
      11: '北京',
      12: '天津',
      13: '河北',
      14: '山西',
      15: '内蒙古',
      21: '辽宁',
      22: '吉林',
      23: '黑龙江 ',
      31: '上海',
      32: '江苏',
      33: '浙江',
      34: '安徽',
      35: '福建',
      36: '江西',
      37: '山东',
      41: '河南',
      42: '湖北 ',
      43: '湖南',
      44: '广东',
      45: '广西',
      46: '海南',
      50: '重庆',
      51: '四川',
      52: '贵州',
      53: '云南',
      54: '西藏 ',
      61: '陕西',
      62: '甘肃',
      63: '青海',
      64: '宁夏',
      65: '新疆',
      71: '台湾',
      81: '香港',
      82: '澳门',
      91: '国外',
    }
    value = value.toUpperCase()
    if (!value || !/^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|30|31)|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}([0-9]|x|X)$/i.test(value)) {
      callback(new Error('身份证号格式错误'))
    } else if (!city[value.substr(0, 2)]) {
      callback(new Error('身份证号格式错误'))
    } else {
      // 18位身份证需要验证最后一位校验位
      if (value.length === 18) {
        const code = value.split('')
        // ∑(ai×Wi)(mod 11)
        // 加权因子
        const factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2]
        // 校验位
        const parity = [1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2]
        let sum = 0
        let ai = 0
        let wi = 0
        for (let i = 0; i < 17; i++) {
          ai = code[i]
          wi = factor[i]
          sum += ai * wi
        }
        if (parity[sum % 11].toString() !== code[17]) {
          callback(new Error())
        }
      } else if (value.length === 15) {
        if (!/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/.test(value)) {
          callback(new Error())
        }
      } else {
        callback(new Error())
      }
    }
    callback()
  },
  /**
   * 字符串转 base64
   * @param str
   * @return {string}
   */
  btoa(str) {
    return window.btoa(str || '')
  },
  /**
   * base64 转 str
   * @param str64
   * @returns {string}
   */
  atob(str64) {
    return window.atob(str64 || '')
  },
  /**
   * obj to base64 str
   * @param obj
   * @return {string}
   */
  objToBase64(obj) {
    return this.btoa(JSON.stringify(obj || {}))
  },
  /**
   * base64 str to obj
   * @param str64
   * @return {any}
   */
  base64ToObj(str64) {
    return JSON.parse(str64 ? this.atob(str64) : '{}')
  },
  /**
   * 判断是否是 null，精准判断
   * @param value
   * @returns {boolean}
   */
  isNull(value) {
    return Object.prototype.toString.call(value) === '[object Null]'
  },
  /**
   * 深拷贝
   * @param obj
   * @returns {any}
   */
  cloneDeepSimple(obj) {
    return JSON.parse(JSON.stringify(obj))
  },
  /**
   * moment 默认格式化
   * @param momentInstance
   * @return {*}
   */
  momentDefaultFormat (momentInstance) {
    return momentInstance.format('YYYY-MM-DD ddd HH:mm:ss')
  }
}

export default $util

