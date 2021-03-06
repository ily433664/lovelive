package com.lovelive.security.utils;

import com.lovelive.security.auth.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 线程的认证对象
 *
 * @author dHe
 */
public class ThreadContext {
    private static final Logger log = LoggerFactory.getLogger(ThreadContext.class);
    public static final String SECURITY_MANAGER_KEY = ThreadContext.class.getName() + "_SECURITY_MANAGER_KEY";
    public static final String SUBJECT_KEY = ThreadContext.class.getName() + "_SUBJECT_KEY";
    private static final ThreadLocal<Map<Object, Object>> resources = new ThreadContext.InheritableThreadLocalMap();

    protected ThreadContext() {
    }

    public static Map<Object, Object> getResources() {
        return (Map) (resources.get() == null ? Collections.emptyMap() : new HashMap((Map) resources.get()));
    }

    public static void setResources(Map<Object, Object> newResources) {
        if (!CollectionUtils.isEmpty(newResources)) {
            ensureResourcesInitialized();
            ((Map) resources.get()).clear();
            ((Map) resources.get()).putAll(newResources);
        }
    }

    private static Object getValue(Object key) {
        Map<Object, Object> perThreadResources = (Map) resources.get();
        return perThreadResources != null ? perThreadResources.get(key) : null;
    }

    private static void ensureResourcesInitialized() {
        if (resources.get() == null) {
            resources.set(new HashMap());
        }

    }

    public static Object get(Object key) {
        if (log.isTraceEnabled()) {
            String msg = "get() - in thread [" + Thread.currentThread().getName() + "]";
            log.trace(msg);
        }

        Object value = getValue(key);
        if (value != null && log.isTraceEnabled()) {
            String msg = "Retrieved value of type [" + value.getClass().getName() + "] for key [" + key + "] bound to thread [" + Thread.currentThread().getName() + "]";
            log.trace(msg);
        }

        return value;
    }

    public static void put(Object key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        } else if (value == null) {
            remove(key);
        } else {
            ensureResourcesInitialized();
            ((Map) resources.get()).put(key, value);
            if (log.isTraceEnabled()) {
                String msg = "Bound value of type [" + value.getClass().getName() + "] for key [" + key + "] to thread [" + Thread.currentThread().getName() + "]";
                log.trace(msg);
            }

        }
    }

    public static Object remove(Object key) {
        Map<Object, Object> perThreadResources = (Map) resources.get();
        Object value = perThreadResources != null ? perThreadResources.remove(key) : null;
        if (value != null && log.isTraceEnabled()) {
            String msg = "Removed value of type [" + value.getClass().getName() + "] for key [" + key + "]from thread [" + Thread.currentThread().getName() + "]";
            log.trace(msg);
        }

        return value;
    }

    public static void remove() {
        resources.remove();
    }

    public static SecurityManager getSecurityManager() {
        return (SecurityManager) get(SECURITY_MANAGER_KEY);
    }

    public static void bind(SecurityManager securityManager) {
        if (securityManager != null) {
            put(SECURITY_MANAGER_KEY, securityManager);
        }

    }

    public static SecurityManager unbindSecurityManager() {
        return (SecurityManager) remove(SECURITY_MANAGER_KEY);
    }

    public static Subject getSubject() {
        return (Subject) get(SUBJECT_KEY);
    }

    public static void bind(Subject subject) {
        if (subject != null) {
            put(SUBJECT_KEY, subject);
        }

    }

    public static Subject unbindSubject() {
        return (Subject) remove(SUBJECT_KEY);
    }

    private static final class InheritableThreadLocalMap<T extends Map<Object, Object>> extends InheritableThreadLocal<Map<Object, Object>> {
        private InheritableThreadLocalMap() {
        }

        protected Map<Object, Object> childValue(Map<Object, Object> parentValue) {
            return parentValue != null ? (Map) ((HashMap) parentValue).clone() : null;
        }
    }
}
