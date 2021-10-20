import moment from 'moment'
function time (value, format = 'YYYY-MM-DD HH:mm:ss') {
  const mmt = moment(value);
  if (mmt.isValid()) {
    return mmt.format(format)
  } else {
    return '-'
  }
}

export default time
