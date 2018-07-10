
## ChangeLog

| Modified By | Date | Change |
|----|----|:-----|
| shiqingshuai   | 20180710  | 1、更新了/user/devices、/user/device接口请求参数。2、新增解绑用户设备接口  |
| shiqingshuai  | 20180708   | 1、新增/user/car_licences 获取车辆列表接口。2、更新了/storage_callback 接口  |
|shiqingshuai   | 20180707  | 1、/user/identity /user/driver_licence更新了字段  |
|shiqingshuai | 20180707  | 1、更新了全局返回的状态码.2、更新了全局参数sign的生成规则 3、更新了请求host，port||
|shiqingshuai | 20180705  | 1、/configuration 接口返回endPoint、bucketName、callbackUrl。2、/storage_token 接口不返回endPoint、bucketName、callbackUrl。3、/storage_token 请求参数删除resource、seq。4、/storage_callback 请求参数增加resource、seq。|
|shiqingshuai | 20180703  |  1、更新了修改用户信息接口的参数说明 |
|shiqingshuai | 20180703  | 1、更新了/user 用户注册接口。|
|shiqingshuai | 20180702  | 1、增加/user 通过手机号查找用户接口。|
|langxuchao| 20180630 | 1. 加入验证码相关接口|
|langxuchao| 20180622 | 1. 增加接口说明|
|langxuchao| 20180605 | 1. 新建|

---

## 接口说明
1.接口前统一加/app/api/v1, 即如接口/app/api/v1/configuraion，则完整路径为/app/api/v1/configuration
2.所有接口均用http POST方法
3、http请求，host='47.93.101.221', port=10156, 如：http://47.93.101.221:10156/app/api/v1/configuration

## 全局参数，参考资源平台全局参数

> 每个请求都要带

```
{
  'uid':'xxxx',
  'sign':'xxxx',
  'request_id':'xxx'
  'req_from':'app',
  'req_ver':'1',
  'minfo':{
  	'os':'android',
  	'os_ver':'2.2',
  	'app_ver':'1.0',
  	'dist':'googleplay',
  	'imei':'fadfasdfadsf',
  	'idfv':'asdf',
  	'mac_addr':'fasdfdsfadf',
  	'network':3,
  	'screen_width':480,
  	'screen_height':720,
  	'device_brand':'Huawei',
  	'device_model':'a330',
  	},
  'geo':{x:'12.203872',y:'-12.231245'},
}
返回
{
    'code':200,
    'msg':'success',
}
```

>请求参数说明

| field | type | null |  desc |
|----|:-----|:-------|:------|
| sign  | string  | no  | 签名字符串，规则：用户登录后，根据前端持久化的token(例：'ssdfdfsfdsfd')，请求的path(例：'/configuration'),生成sign, toke与path用下划线拼接进行md5加密后的32位小写字符串。 sign=md5("ssdfdfsfdsfd_/configuraion")  |
| request_id | string | no | 每个请求唯一,可用imei-mac-timestamp生成的md5作为request_id |  |
| req_from | string | no  | 表示用户请求来源 |
| req_ver | int | no  | 请求版本 |
| os | string | no  | 操作系统 |
| os_ver | string | no  | 操作系统版本 |
| app_ver | string | no  | app版本 |
| dist | string | no  | 渠道 |
| imei | string | no  | android设备,imei |
| idfv | string | no  | ios设备, 表示ios的identifierForVendor标识|
| mac_addr | string | no  | mac地址 |
| network | int | no  | 取值{-1,0,2,3,4}, 分别表示{无法取到网络状态，wifi,2G,3G,4G} |
| screen_width | int | no  | 屏幕宽度 |
| screen_height | int | no  | 屏幕高度 |
| device_brand | string | no  | 手机品牌 |
| device_model | string | no  | 手机型号 |
| geo | json | no  | 表示经纬度信息，其中x表示纬度，y表示经度，均为字符串, 为空字符串表示无法获得经纬度 |

>返回参数说明

| field | type | null |  desc |
|----|:-----|:-------|:------|
| code | int | no | 返回的状态码, 其中若code位于[400,500)均为请求失败, 具体值及含义如下,|
|  | int | no | 200,表示请求成功 |
|  | int | no | 400,表示请求失败 |
|  | int | no | 401,表示未登录 |
|  | int | no | 402,用户不存在 |
|  | int | no | 403,登录密码错误|
|  | int | no | 404,注册使用的邀请码无效 |
|  | int | no | 430,表示注册的手机号在数据库中已存在 |
|  | int | no | 433,表示无权限进行此操作 |
| msg | string | no  | 接口提示信息 |

---

### 获取app的一些配置信息
```
/configuration
参数:
{
	'action':'get'
}

返回值
{
  'code':200,
  'msg':'success',
  'data':{
    "oss":{
      "endPoint":"oss-cn-beijing.aliyuncs.com",
      "bucketName":"bi-shu",
      "callbackUrl":"http://47.93.101.221:10156/app/api/v1/storage_callback",
    },
    "force_update":0,
    "top_version":{
      'ver_code":'2.0.1',
      'content":['1.xxxx','2.xxxx','3.xxxx']
    }
  }
}
```

>请求参数说明

| field | type | null |  desc |
|----|:-----|:-------|:------|
| endPoint | string | no |  表示oss EndPoint, 阿里图片上传接口用,默认'oss-cn-beijing.aliyuncs.com'|
| bucketName | string | no  | 存储bucket名称，默认'bi-shu'|
| callbackUrl | string | no | 表示oss回调地, 默认'http://47.93.101.221:10156/app/api/v1/storage_callback'    |
| force_update | int |   | 整型，取值{0,1}, 为1时，表示客户端版本过旧已不支持此客户端, 需进行更新方可用|
| top_version | string | no | 当前最新版本相关信息   |
| | | |  注意:对于/configuration返回的每一项参数，客户端均进行本地化存储，当获取不到时，用上一次存储的参数，对于新装机且第一次就请求失败用户，用客户端写死的默认值 |

---

### 我的积分

```
/user/score
参数:
{
  'action':'get',
  '全局参数'
}
返回值
{
  'code':200,
  'msg':'success',
  'data':{
    'score':30000,
    'score_add_yesterday':10000,
  }
}
```

---

### 积分获取记录(我的钱包)

```
/user/score_log
参数:
{
  'action':'get',
  '全局参数'
}
返回值
{
  'code':200,
  'msg':'success',
  'data':{
    'list':[
      {
        'title':'xxxx',
        'value':10,
        'time':1232111,
      },
      ....
    ]
  }
}

```

---

### 获取用户行程

```
/user/routes
参数:
{
  'action':'get',
  'page':0,
  'page_size':10,
  '全局参数'
}
返回
{
  'code':200,
  'msg':'success',
  'data':{
    'total':100,
    'page':0,
    'page_size':10,
    'list':[
      {
        'start_time':11234121,
        'end_time':11234121,
        'sale_status':0,
        'score_add':5,
      },
      ...
    ]
  }
}
```

---

### 获取用户信息(个人中心)
```
/user/info
参数:
{
  'action':'get',
  '全局参数'
}
返回
{
  'code':200,
  'msg':'success',
  'data':{
      'avatar':'http://xxxx',
      'nickname':'',
      'gender':1,
      'id_verified':0,
      'driver_verified':0,
      'car_verified':0,
      'devices':[
        {
          'name':'xxxx',
          'type':0,
          'imei':'xxx',
        },
        ...
      ],
  }
}
```

### 用户注册
```
/user
参数
{
  'action':'post',
  'mobile':'13888888888',
  'pwd':'xxxxx',
  'invite_code': 'xxxxx',
  '全局参数'
}
返回
{
  'code':200,
  'msg':'success',
  'data':{
    'uid':'xxxx',
    'token':'xxx',
    'expired_time': 12345
  }
}
```
>请求参数说明

| field | type | null |  desc |
|----|:-----|:-------|:------|
| invite_code | string | yes | 邀请码（可选） |


###用户查找
```
/user
参数
{
  'action':'post',
  'mobile':'13888888888',
  '全局参数'
}
返回
{
  'code':200,
  'msg':'success',
  'data':{
    'uid':'xxxx',
  }
}

data.uid 为空字符串时表示用户未注册，不为空时用户已注册
```

---

### 设置/修改密码
```
/user/pwd
参数
{
  'action':'put',
  'pwd':'xxxxx',
  '全局参数'
}
返回
{
  'code':200,
  'msg':'success',
}
```

---

### 填写邀请码
```
/user/from/invite_code
参数
{
  'action':'put',
  'invite_code':'xxxxx',
  '全局参数'
}
返回
{
  'code':200,
  'msg':'success',
}
```

---

### 登录
```
/user/token
参数
{
  'action':'post',
  'mobile':'13888888888',
  'pwd':'xxxxx',
  '全局参数'
}
返回
{
  'code':200,
  'msg':'success',
  'data':{
    'uid':'xxx',
    'token':'xxx',
    'expired_time':11234,
  }
}
```

---

### 刷新token
```
/user/token
参数
{
  'action':'put',
  '全局参数'
}
返回
{
  'code':200,
  'msg':'success',
  'data':{
    'uid':'xxx',
    'token':'xxx',
    'expired_time':11234,
  }
}
```


---

### 退出登录
```
/user/token
参数
{
  'action':'delete',
  '全局参数'
}
返回
{
  'code':200,
  'msg':'success',
}
```

---

### 用户信息修改
```
/user/info
参数
{
  'action':'put',
  'nickname':'xxx',
  'gender':0,
  'message_alert':0,
  '全局参数'
}
返回
{
  'code':200,
  'msg':'success',
}
```
>请求参数说明

| field | type | null |  desc |
|----|:-----|:-------|:------|
| nickname | string | no  |昵称，修改的时候才需要此参数, 不修改的时候不需要此参数|
| gender | int | no  | 取值范围[0, 1] 分别表示['女', '男']，修改的时候才需要此参数，不修改的时候不需要此参数|
| message_alert | int  | no  | 取值范围[0, 1] 分别表示['关闭', '开启']  , 修改的时候才需要此参数，不修改的时候不需要此参数|
---

### 用户反馈
```
/user/feedback
参数
{
  'action':'post',
  'content':'xxxx',
  '全局参数'
}
返回
{
  'code':200,
  'msg':'success',
}
```

---

### 获取用户智能硬件
```
/user/devices
参数
{
  'action':'get',
  '全局参数'
}
返回
{
  'code':200,
  'msg':'success',
  'data':{
    'list':[
      {
        'id':'xxxx',
        'name':'xxx',
        'imei':'xxxx',
      },
      ...
    ]
  }
}

```

---

### 添加用户智能硬件
```
/user/device
参数
{
  'action':'post',
  'imei':'xxxx',
  'name':'xxxxx',
  '全局参数'
}
返回
{
  'code':200,
  'msg':'success',
}
```
>请求参数说明

| field | type | null |  desc |
|----|:-----|:-------|:------|
| imei | string | no |设备的唯一标识 |
| name  | string  | no  | 设备名  |

### 解绑用户智能硬件
```
/user/device
参数
{
  'action':'delete',
  'id': 'xxxx',
  '全局参数'
}
返回
{
  'code':200,
  'msg':'success',
}
```
>请求参数说明

| field | type | null |  desc |
|----|:-----|:-------|:------|
| id | string | no | user_device_id用户设备ID， 设备列表中返回的用户设备id|
---

### 发现
```
/user/discovery
参数
{
  'action':'get',
  '全局参数'
}
返回
{
  'code':'xxx',
  'msg':'xxx',
  'data':{
    'weather':{
        'temperature':'13',
        'desc':'多云',
    },
    'location':{
      'city':'北京',
      'district':'海淀区',
      'detail':'西直门'
    },
    'traffic_restrict':{
      'restrict_number':['5','9']
    }
    'cars':[
        {
          'plate_number':'京A123456',
          'violation':{
            'unsolved_num':2,
            'score_cost':12,
            'money_cost':1000,
          }
        }
    ]
    ,
  }
}
```
>返回参数说明

| field | type | null |  desc |
|----|:-----|:-------|:------|
| restrict_number | list |  | 限行尾号|
| plate_number  | string  |   | 车牌号  |
| unsolved_num  | int  |   | 违章次数  |
| score_cost  | int  |   | 扣分  |
| money_cost | int  |   | 罚款 |
| violation  | dict  |   | 空字典时表示未查询成功查询  |
---

### 获取oss配置及token

```
/storage_token
参数
{
  'action':'get',
  '全局参数'
}
返回
{
  'status':200,
  'msg':'success',
  'data':{
    'AccessKeyId':'STS.3pYjsdgdgagdasdg',
    'AccessKeySecret':'rpnwO9kvEgetGdrddgsR2YrTtI',
    'Security':'CAES+wMIARKAxxxxxx',
    'Expiration':234134,
  }
}
```

>请求参数说明

| field | type | null |  desc |
|----|:-----|:-------|:------|
| resource | string | no  | {'identity'} |
| seq | int | no  | 表示图片顺序，取值{0,1}, 表示{正面，反面} |

>返回参数说明

| field | type | null |  desc |
|----|:-----|:-------|:------|
| AccessKeyId | string | no  | 表示Android/iOS应用初始化OSSClient获取的 AccessKeyId |
| AccessKeySecret | string | no  | 表示Android/iOS应用初始化OSSClient获取AccessKeySecret |
| SecurityToken | string | no  |  表示Android/iOS应用初始化的Token |
| Expiration  | float  | no  |   |
---

### oss回调

```
/storage_callback
参数
{
  'bucket':'',
  'object':'',
  'etag':'',
  'size':123,
  'mimeType':'',
  'imageInfo':{
    'height':'',
    'width':'',
    'format':'',
  },
  'x:action': 'post',
  'x:resource':'xxxx',
  'x:id': 'xxxxxxx',
  'x:seq':0,
  'x:全局参数'
}
返回参数
{
  'code':200
}
```
>请求参数说明

| field | type | null |  desc |
|----|:-----|:-------|:------|
| bucket  | string  |   |   |
| object  | string  |   |   |
| etag   |  string |   |   |
| size  | float  |   |   |
| mimeType   | string  |   |   |
| imageInfo   | dict  |   | 以上参数为系统参数，请参照OSS文档  |
| resource  | string  | no  | 文件类型，取值['id_card', 'driver_licence', 'car_licence']  |
| id   | string  | yes  | 用户证书id,当resource='car_licence'时并且用户修改车辆行驶证的时候需要上传此参数,不上传此参数或此参数为空字符串表示新建证书|
| seq   | int  | no  | 表示文件的正反面，0表示正面，1表示反面，默认为0  |
|   |   |   | 为了和OSS系统参数做区分，其他参数名必须加前缀'x:'  |
|   |   |   | 注意:android及ios客户端构造oss CallBack参数时，callbackBodyType值需设为application/json，具体可参考'https://help.aliyun.com/document_detail/31989.html'|
|   |   |   |  注意:客户端存储图片时, 需将图片存储到以用户uid命名的目录下, 即图片存储位置为<bucket>下的<object>, object命名规则 '<uid>/<resource>_<unix时间戳>.png'|
---

### 获取身份证信息
```
/user/identity
参数
{
  'action':'get',
  '全局参数'
}
返回
{
  'code':200,
  'msg':'success'
  'data':{
    'verified_status':2,
    'verified_msg': 'xxxxxxx',
    'name':'2321'
    'id_number':'2321',
    'issuing_authority':'北京市公安局',
    'start_ts':123456,
    'end_ts':123456,
    'back_img':'http://xx',
    'front_img':'http://xxx'
  }
}
```
>返回参数说明

| field | type | null |  desc |
|----|:-----|:-------|:------|
| verified_status | int | no | 认证状态 {0, 1, 2, 3, 4},分别表示{未认证, 认证中，认证成功，认证失败, 重复认证}|
| verified_msg   | string  | no  | 认证提示信息，认证不成功时，该字段不为空  |
| name  | string  |   | 姓名  |
| id_number  | string  |   | 身份证号  |
| issuing_authority  | string  |   |   |
| start_ts | float  |   | unix时间戳 开始时间  |
| end_ts   | float |   | unix时间戳 截止时间  |
| back_img   | string  |   | 反面  |
| front_img   | string  |   | 正面  |
---

### 获取驾照信息

```
/user/driver_licence
参数
{
  'action':'get',
  '全局参数'
}
返回
{
  'code':200,
  'msg':'success'
  'data':{
    'verified_status':2,
    'verified_msg': 'xxxxxxx',
    'name':'2321'
    'id_number':'2321',
    'start_ts':1232435,
    'end_ts:21423543,
    'class':'c1'
    'back_img':'http://xx',
    'front_img':'http://xxx'
  }
}
```
>返回参数说明

| field | type | null |  desc |
|----|:-----|:-------|:------|
| verified_status | int | no | 认证状态{0, 1, 2, 3, 4},分别表示{未认证, 认证中，认证成功，认证失败, 重复认证} |
| verified_msg   | string  | no  | 认证提示信息，认证不成功时，该字段不为空  |
| name  | string  |   | 姓名  |
| id_number  | string  |   | 驾驶证号  |
| start_ts | float  |   | unix时间戳 开始时间  |
| end_ts   | float |   | unix时间戳 截止时间  |
| class   | string  |   | 驾驶证类型  |
| back_img   | string  |   | 反面  |
| front_img   | string  |   | 正面  |

---
###获取车辆行驶证列表
```
/user/car_licences
参数
{
  'action': 'get',
  '全局参数'
}
返回
{
  'code': 200,
  'msg': 'success',
  'data':{
    'list':[
      {
          "plate_number": "沪E1527",
          "id": "99ea8ba181c811e8831c4a0006c56981",
          "verified_status": 2
      }
    ]
  }
}
```
>返回参数说明

| field | type | null |  desc |
|----|:-----|:-------|:------|
| verified_status | int | no | 认证状态{0, 1, 2, 3, 4},分别表示{未认证, 认证中，认证成功，认证失败, 重复认证} |
| plate_number  | string  |   | 车牌号  |
| id   | string  |   | 车辆行驶证id  |


### 获取行驶证信息
```
/user/car_licence
参数
{
  'action':'get',
  'id': 'xxxxx',
  '全局参数'
}
返回
{
  'code':200,
  'msg':'success',
  'data':{
    'verified_status':2,
    'verified_msg': 'xxxxxxx',
    'plate_number':'2321'
    'owner':'王大白'
    'id_number':'2321',
    'engine_no':'134123',
    'vehicle_type':'小型越野客车',
    'vehicle_model':'普拉多J134123TEBX#$FJ',
    'back_img':'http://xx',
    'front_img':'http://xxx'
  }
}
```
>请求参数说明

| field | type | null | desc |
|----|:-----|:-------|:------|
| id   | string  | no  | 车辆行驶证id  |
>返回参数说明

| field | type | null |  desc |
|----|:-----|:-------|:------|
| verified_status | int | no | 认证状态{0, 1, 2, 3, 4},分别表示{未认证, 认证中，认证成功，认证失败, 重复认证} |
| verified_msg   | string  | no  | 认证提示信息，认证不成功时，该字段不为空  |
| plate_number  | string  |   | 车牌号  |
| owner   | string  |   | 车主姓名  |
| id_number  | string  |   | 车辆识别码   |
| engine_no   | string  |   | 发动机号码  |
| vehicle_type   | string  |   | 车类型  |
| vehicle_model   | string  |   | 车辆型号  |
| back_img   | string  |   | 反面  |
| front_img   | string  |   | 正面  |
---

### 发送手机验证码
```
/sms_code
参数
{
  'action':'post'
  'mobile':'13800000000'
}
返回
{
  'code':200,
  'msg':'success'
}
```

---

### 验证手机验证码
```
/sms_code
参数
{
  'action':'get'
  'mobile':'13800000000'
  'verify_code':'1382'
}
返回
{
  'code':200,
  'msg':'success'
  'data':{
    'verified':1
  }
}
```

>请求参数说明

| field | type | null |  desc |
|----|:-----|:-------|:------|
| mobile | int | no  | 手机号 |
| verify_code | string | no  | 取值范围['0000','9999'] |

>返回参数说明

| field | type | null |  desc |
|----|:-----|:-------|:------|
| verified | int | no | 取值{0,1}, 分别表示{验证失败，验证成功} |
