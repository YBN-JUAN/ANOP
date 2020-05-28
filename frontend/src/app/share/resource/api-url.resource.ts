export class ApiUrlResource {
  private static URL_PREFIX = 'http://localhost:8080'

  private static _PUB_GROUPS = '/v1/not/pub/groups'

  static get PUB_GROUPS(): string {
    return this.URL_PREFIX + this._PUB_GROUPS;
  }

  private static _PUB_REQUESTS = '/v1/not/pub/requests'

  static get PUB_REQUESTS(): string {
    return this.URL_PREFIX + this._PUB_REQUESTS;
  }

  private static _SUB_GROUPS = '/v1/not/sub/groups'

  static get SUB_GROUPS(): string {
    return this.URL_PREFIX + this._SUB_GROUPS;
  }

  private static _CATEGORIES = '/v1/cat/categories'

  static get CATEGORIES(): string {
    return this.URL_PREFIX + this._CATEGORIES;
  }

  private static _TODO = '/v1/tod/todos'

  static get TODO(): string {
    return this.URL_PREFIX + this._TODO;
  }

  private static _SIGN_UP = '/v1/usr/signup'

  static get SIGN_UP(): string {
    return this.URL_PREFIX + this._SIGN_UP;
  }

  private static _LOGIN = '/login'

  static get LOGIN(): string {
    return this.URL_PREFIX + this._LOGIN;
  }

  private static _profileUrl = '/v1/usr/profile'

  static get profileUrl(): string {
    return this.URL_PREFIX + this._profileUrl;
  }

  private static _AVATAR = '/v1/usr/avatar'

  static get AVATAR(): string {
    return this.URL_PREFIX + this._AVATAR;
  }

  private static _userUrl = '/v1/usr/user'

  static get userUrl(): string {
    return this.URL_PREFIX + this._userUrl;
  }
}

