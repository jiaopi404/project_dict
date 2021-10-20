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
  async formValidate (formComp, message) {
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
  throttle (fn, milliseconds) {
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
  flatToTree (data, fields = { ID: 'id', PARENT: 'parentId', CHILDREN: 'children' }) {
    if (typeof fields !== 'object') {
      fields = { ID: 'id', PARENT: 'parentId', CHILDREN: 'children' }
    }
    const ID = fields.ID || 'id'
    const PARENT = fields.PARENT || 'parentId'
    const CHILDREN = fields.CHILDREN || 'children'
    const parentIdMap = {
      root: []
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
  connector (result, key, parentMap, { CHILDREN, ID }) {
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
  treeReduce (data, fn, prev = 0) {
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
  to10Thousand (number) {
    return Number(((number) / 10000).toFixed(6))
  },
  /**
   * 判断两个数字是否在误差范围内相等
   * @param num1
   * @param num2
   * @param precision 精度， 10^-precision
   * @return {boolean}
   */
  ifNumberEqual (num1, num2, precision = 6) {
    return Math.abs(num1 - num2) < Math.pow(10, -1 * precision)
  },
  /**
   * 获取欢迎语
   * @return {string}
   */
  getGreetingNote () {
    const hour = new Date().getHours()
    if (hour <5) {
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
  toFixedNumber (num, precision = 2) {
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
  pageSelectedForSelectionChangeCustomProp (selected, selection, tableData, prop = 'id') {
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
  getNameRecursive (obj, nameArr = [], fields = { name: 'name', parent: 'parent' }, connector = '/') {
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
  cleanForeignKey (data, foreignKeyPropList) {
    foreignKeyPropList.forEach(item => {
      data[item] = data[item] ? { id: data[item].id } : null
    })
  },
  /**
   * 生成 uuid
   * @return {string}
   */
  uuid() {
    let s = [];
    let hexDigits = "0123456789abcdef";
    for (let i = 0; i < 36; i++) {
      s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
    }
    s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
    s[8] = s[13] = s[18] = s[23] = "-";

    return s.join("");
  }
}

export default $util

