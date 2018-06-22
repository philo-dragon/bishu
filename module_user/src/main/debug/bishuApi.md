
## ChangeLog

| Modified By | Date | Change |
|----|----|:-----|
|langxuchao| 20180605 | 1. 新建|

---

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
	'status':200,
	'data':{
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

### 注册
```
/user
参数
{
  'action':'post',
  'mobile':'13888888888',
  'verify_code':'xxxxx',
}
返回
{
  'code':200,
  'msg':'success',
  'data':{
    'uid':'xxxx',
    'token':'xxx',
  }
}
```

---

### 设置/修改密码
```
参数
{
  'action':'put',
  'pwd':'xxxxx',
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
}
返回
{
  'code':200,
  'msg':'success',
}
```

---

### 用户反馈
```
/user/feedback
参数
{
  'action':'post',
  'content':'xxxx',
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
        'type':'xxxx',
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
  'type':'xxxxx'
}
返回
{
  'code':200,
  'msg':'success',
}
```

---

### 发现
```
/user/discovery
参数
{
  'action':'get'
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
    'car':{
      'plate_number':'京A123456',
      'violation':{
        'unsolved_num':2,
        'score_cost':12,
        'money_cost':1000,
      }
    }
    ,
  }
}
```

---

### 获取oss配置及token

```
/storage_token
参数
{
  'action':'get',
  'resource':'identity',
  'seq':0,
}
返回
{
  'status':200,
  'msg':'success',
  'data':{
    "endPoint":"oss-cn-shanghai.aliyuncs.com",
    "bucketName":"bishu",
    "callbackUrl":"http://106.14.224.126/storage_callback",
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
| endPoint | string | no |  表示oss EndPoint, 阿里图片上传接口用|
| bucketName | string | no  | 存储bucket名称 |
| callbackUrl | string | no | 表示oss回调地址    |
| AccessKeyId | string | no  | 表示Android/iOS应用初始化OSSClient获取的 AccessKeyId |
| AccessKeySecret | string | no  | 表示Android/iOS应用初始化OSSClient获取AccessKeySecret |
| SecurityToken | string | no  |  表示Android/iOS应用初始化的Token |
|  |  |  | 注意:android及ios客户端构造oss CallBack参数时，callbackBodyType值需设为application/json，具体可参考'https://help.aliyun.com/document_detail/31989.html' <br/>
|  |  |  |注意:客户端存储图片时, 需将图片存储到以用户uid命名的目录下, 即图片存储位置为'<bucket_name>/<uid>/<图片名，例如id,driver_licence等>_<unix时间戳>.png' |

---

### oss回调

```
/storage_callback
```

---

### 获取身份证信息
```
/user/identity
参数
{
  'action':'get'
}
返回
{
  'code':200,
  'msg':'success'
  'data':{
    'verified_status':2,
    'name':'2321'
    'id_number':'2321',
    'issuing_authority':'北京市公安局',
    'start_day':'2018-05-01',
    'end_day':'2018-05-02',
    'back_img':'http://xx',
    'front_img':'http://xxx'
  }
}
```

---

### 获取驾照信息

```
/user/driver_licence
参数
{
  'action':'get'
}
返回
{
  'code':200,
  'msg':'success'
  'data':{
    'verified_status':2,
    'name':'2321'
    'id_number':'2321',
    'issuing_authority':'北京市公安局',
    'start_day':'2018-05-01',
    'end_day':'2018-09-01',
    'class':'c1'
    'back_img':'http://xx',
    'front_img':'http://xxx'
  }
}
```

---

### 获取行驶证信息
```
/user/car_licence
参数
{
  'action':'get'
}
返回
{
  'code':200,
  'msg':'success'
  'data':{
    'verified_status':2,
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
