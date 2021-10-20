import FormValidationError from '@/util/exception/FormValidationError'
import $config from '@/config/config'

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
  }
}

export default $util
